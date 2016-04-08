package ru.dimall.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import ru.dimall.implementations.Firmlist;
import ru.dimall.implementations.Firmobject;
import ru.dimall.implementations.GisHttpUrlConnection;
import ru.dimall.interfaces.*;
import ru.dimall.services.GisService;

import java.util.*;
import java.util.concurrent.*;

/**
 * http request controller.
 * Receive and perform request
 */
@Controller
public class GisController {

    private IGisService gisService;

    @Autowired
    private WebApplicationContext context;

    public IGisService getGisService() {
        return gisService;
    }

    @Autowired
    public void setGisService(IGisService gisService) {
        this.gisService = gisService;
    }

    /**
     * Class for combining WHOLE info about company
     */
    private class Worker implements Callable<IFirmList> {

        private IFirmList<?> firms;

        private Map<String,String> requestParameters;

        private IGisService gisService;

        public Worker(Map<String,String> requestParameters,IGisService gisService) {
            this.setRequestParameters(requestParameters);
            this.setGisService(gisService);
        }

        /**
         * get reference on service
         * @return reference on service
         */
        public IGisService getGisService() {
            return gisService;
        }

        /**
         * set reference on service
         * @param gisService service
         */
        public void setGisService(IGisService gisService) {
            this.gisService = gisService;
        }

        /**
         * get map of request parameters
         * @return map of request parameters
         */
        public Map<String,String> getRequestParameters() {
            return this.requestParameters;
        }

        /**
         * set map of request parameters
         * @param requestParameters map of request parameters
         */
        public void setRequestParameters(Map<String,String> requestParameters) {
            this.requestParameters = requestParameters;
        }

        /**
         * get list of organizations
         * @return list of organizations
         */
        public IFirmList getFirms() {
            return this.firms;
        }

        @Override
        public IFirmList call() {
            return this.getGisService().getInfo(this.getRequestParameters());
        }

    }

    /**
     * Execute information request
     * @param requestParameters
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getInfo(@RequestParam HashMap<String,String> requestParameters) {

        List<Future> threads = new ArrayList();
        String[] cities = this.getGisService().getAppData().getData().get("cities").split(",");
        ExecutorService executor = Executors.newFixedThreadPool(cities.length);

        for (String city : cities) {
            Map<String,String> newRequestParameters = (HashMap)requestParameters.clone();
            newRequestParameters.put("city",city);
            Worker worker = new Worker(newRequestParameters,(IGisService)context.getBean("gisService"));
            Future<IFirmList> future = executor.submit(worker);
            threads.add(future);
        }
        executor.shutdown();

        IFirmList firms = null;
        Iterator iterator = threads.iterator();
        while(iterator.hasNext()) {
            try {
                if (firms == null)
                    firms = (IFirmList)((Future) iterator.next()).get();
                else
                    firms.getFirms().addAll(((IFirmList)((Future) iterator.next()).get()).getFirms());
            } catch (InterruptedException | ExecutionException e) {
                return "[{}]";
            }
        }

        return firms.toString(Integer.parseInt(requestParameters.get("pagesize")));
    }

}
