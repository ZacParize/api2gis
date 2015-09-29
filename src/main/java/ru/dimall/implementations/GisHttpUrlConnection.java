package ru.dimall.implementations;

import ru.dimall.interfaces.IFirmList;
import ru.dimall.interfaces.IGisDirectory;
import ru.dimall.interfaces.IGisFlamp;
import ru.dimall.interfaces.IHttpUrlConnection;
import java.io.*;
import java.net.*;

/**
 *
 * @author  franco
 * @see IHttpUrlConnection
 */
public class GisHttpUrlConnection implements IHttpUrlConnection {

    private String protocol = "http";
    private String userAgent = "Mozilla/5.0";
    private String service = "catalog.api.2gis.ru";
    private String serviceMethod = "search";
    private String userKey = "ruuxah6217";
    private String parameters = "";
    {
        try {
            parameters = "key=" + this.getUserKey() + "&version=1.3&what=" + URLEncoder.encode("пиво", "UTF-8") + "&where=" + URLEncoder.encode("Новосибирск", "UTF-8") + "&sort=name&page=1&pagesize=50&output=json";
        } catch (UnsupportedEncodingException e) {
            parameters = "";
        }
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String getServiceMethod() {
        return serviceMethod;
    }

    @Override
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    @Override
    public String getUserKey() {
        return userKey;
    }

    @Override
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public String getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @return
     */
    @Override
    public String sendGet() {

        StringBuffer response = new StringBuffer();

        try {
            String url = this.getProtocol() + "://" + this.getService() + "/" + this.getServiceMethod() + "?" + this.getParameters();
            URL object = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) object.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", this.getUserAgent());

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"))) {
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
            } else {
                response.append("Bad response from remote service. Please, try later");
            }
        } catch (MalformedURLException e) {
            response.delete(0, response.length());
            response.append("Service temporary unavailable. Please, try later");
        } catch (ProtocolException e) {
            response.delete(0,response.length());
            response.append("Inner service problems. Please, try later");
        } catch (IOException e) {
            response.delete(0,response.length());
            response.append("Could not read response. Please, try later");
        }

        return response.toString();

    }

    /**
     *
     * @return
     */
    @Override
    public String sendPost() {
        return null;
    }

}
