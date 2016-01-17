package ru.dimall.implementations;

import ru.dimall.interfaces.IHttpUrlConnection;
import java.io.*;
import java.net.*;

/**
 *
 * Implementation of work over http
 *
 * @author  franco
 * @see IHttpUrlConnection
 */

public class GisHttpUrlConnection implements IHttpUrlConnection, Cloneable {

    private String protocol;
    private String userAgent;
    private String service;
    private String serviceMethod;
    private String userKey;
    private String parameters;

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

    /**
     * Deep clone (all fields are immutable)
     *
     * @return
     */
    public IHttpUrlConnection clone() {
        try {
            return (IHttpUrlConnection) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}