package ru.dimall.interfaces;

/**
 *
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
     *
     * @return
     */
    String sendGet();

    /**
     *
     * @return
     */
    String sendPost();

}
