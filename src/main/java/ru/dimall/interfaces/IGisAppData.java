package ru.dimall.interfaces;

/**
 *
 */
public interface IGisAppData {

    String getProtocol();

    void setProtocol(String protocol);

    String getUserAgent();

    void setUserAgent(String userAgent);

    String getService();

    void setService(String service);

    String getUserKey();

    void setUserKey(String userKey);

    String getSearchingMethod();

    void setSearchingMethod(String searchingMethod);

    String getRatingMethod();

    void setRatingMethod(String ratingMethod);

}
