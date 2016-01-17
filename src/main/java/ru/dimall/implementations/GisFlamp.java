package ru.dimall.implementations;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisFlamp;
import ru.dimall.interfaces.IHttpUrlConnection;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * Kind of flamp
 *
 * @author  franco
 * @see IGisFlamp
 * @see Firmobject
 */

public class GisFlamp implements IGisFlamp<Firmobject> {

    private class Worker extends Thread {

        private Firmobject firm;

        private IHttpUrlConnection connection;

        public Firmobject getFirm() {
            return this.firm;
        }

        public void setFirm(Firmobject firm) {
            this.firm = firm;
        }

        public IHttpUrlConnection getConnection() {
            return connection;
        }

        public void setConnection(IHttpUrlConnection connection) {
            this.connection = connection;
        }

        /**
         *
         * @param jsonResponse
         * @return
         */
        private double getRatingFromResponse(String jsonResponse) {

            if (jsonResponse != null) {

                JSONParser parser = new JSONParser();

                try {
                    Object object = parser.parse(jsonResponse);
                    return Double.parseDouble(String.valueOf(((JSONObject) object).get("rating")));
                } catch (ParseException e) {
                    return 0;
                }

            }

            return 0;

        }

        @Override
        public void run() {

            if (this.getFirm() != null && this.getConnection() != null) {

                try {
                    this.getConnection().setParameters("key=" + this.getConnection().getUserKey() + "&version=1.3&id=" + URLEncoder.encode(String.valueOf(this.getFirm().getId()), "UTF-8") + "&hash=" + URLEncoder.encode(this.getFirm().getHash(), "UTF-8") + "&output=json");
                    if (this.getConnection().getParameters() != null && this.getConnection().getParameters() != "") {
                        this.getFirm().setRating(this.getRatingFromResponse(this.getConnection().sendGet()));
                    }
                } catch (UnsupportedEncodingException ex) {
                    firm.setRating(0);
                }

            }

        }

    }

    private IHttpUrlConnection connection;

    /**
     *
     * @return
     */
    @Override
    public IHttpUrlConnection getConnection() {
        return connection;
    }

    /**
     *
     * @param connection
     */
    @Autowired
    @Override
    public void setConnection(IHttpUrlConnection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param firms
     * @return
     */
    @Override
    public void fillRating(IFirmList<Firmobject> firms) {

        if (firms != null && firms.getFirms() != null && firms.getFirms().size() > 0  && this.getConnection() != null) {

            //ExecutorService executor = Executors.newFixedThreadPool(firms.getFirms().size());

            for (Firmobject firm : firms.getFirms()) {

                /*try {
                    this.getConnection().setParameters("key=" + this.getConnection().getUserKey() + "&version=1.3&id=" + URLEncoder.encode(String.valueOf(firm.getId()), "UTF-8") + "&hash=" + URLEncoder.encode(firm.getHash(), "UTF-8") + "&output=json");
                    if (this.getConnection().getParameters() != null && this.getConnection().getParameters() != "") {
                        firm.setRating(this.getRatingFromResponse(this.getConnection().sendGet()));
                    }
                } catch (UnsupportedEncodingException ex) {
                    firm.setRating(0);
                } catch (Exception ex) {
                    firm.setRating(0);
                }*/
                Worker worker = new Worker();
                worker.setFirm(firm);
                worker.setConnection(((GisHttpUrlConnection)this.getConnection()).clone());
                worker.start();
                try {
                    worker.join();
                } catch (InterruptedException e) {
                    firm.setRating(0.0);
                }
                //executor.execute(worker);
            }

            /*executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }

    }

}
