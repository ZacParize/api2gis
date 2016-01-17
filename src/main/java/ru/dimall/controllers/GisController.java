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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
     *
     */
    private class Worker extends Thread {

        private IFirmList<?> firms;

        private Map<String,String> requestParameters;

        private IGisService gisService;

        /**
         *
         * @return
         */
        public IGisService getGisService() {
            return gisService;
        }

        /**
         *
         * @param gisService
         */
        public void setGisService(IGisService gisService) {
            this.gisService = gisService;
        }

        /**
         *
         * @return
         */
        public Map<String,String> getRequestParameters() {
            return this.requestParameters;
        }

        /**
         *
         * @param requestParameters
         */
        public void setRequestParameters(Map<String,String> requestParameters) {
            this.requestParameters = requestParameters;
        }

        /**
         *
         * @return
         */
        public IFirmList getFirms() {
            return this.firms;
        }

        @Override
        public void run() {
            this.firms = this.getGisService().getInfo(this.getRequestParameters());
        }

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getInfo(@RequestParam HashMap<String,String> requestParameters) {

        List<Thread> threads = new ArrayList();

        for (String city : this.getGisService().getAppData().getData().get("cities").split(",")) {

            Map<String,String> newRequestParameters = (HashMap)requestParameters.clone();
            newRequestParameters.put("city",city);
            Worker worker = new Worker();
            worker.setRequestParameters(newRequestParameters);
            worker.setGisService((IGisService)context.getBean("gisService"));
            threads.add(worker);
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException e) {
                threads.remove(worker);
            }
        }

        IFirmList firms = null;
        Iterator iterator = threads.iterator();
        while(iterator.hasNext()) {
            if (firms == null)
                firms = ((Worker) iterator.next()).getFirms();
            else
                firms.getFirms().addAll(((Worker) iterator.next()).getFirms().getFirms());
        }

        return firms.toString(Integer.parseInt(requestParameters.get("pagesize")));
    }

}
