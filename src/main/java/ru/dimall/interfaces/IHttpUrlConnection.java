package ru.dimall.interfaces;

/**
 * http connection functionality
 */
public interface IHttpUrlConnection {

    String getProtocol();

    void setProtocol(String protocol);

    String getUserAgent();

    void setUserAgent(String userAgent);

    String getService();

    void setService(String service);

    String getServiceMethod();

    void setServiceMethod(String serviceMethod);

    String getUserKey();

    void setUserKey(String userKey);

    String getParameters();

    void setParameters(String parameters);

    /**
     * implementation of get request
     * @return
     */
    String sendGet();

    /**
     * implementation of post request
     * @return
     */
    String sendPost();

}
