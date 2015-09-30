package ru.dimall.components;

import org.springframework.stereotype.Component;
import ru.dimall.interfaces.IGisAppData;

@Component
public class GisAppData implements IGisAppData {

    private String protocol;

    private String userAgent;

    private String service;

    private String userKey;

    private String searchingMethod;

    private String ratingMethod;

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
    public String getUserKey() {
        return userKey;
    }

    @Override
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public String getSearchingMethod() {
        return searchingMethod;
    }

    @Override
    public void setSearchingMethod(String searchingMethod) {
        this.searchingMethod = searchingMethod;
    }

    @Override
    public String getRatingMethod() {
        return ratingMethod;
    }

    @Override
    public void setRatingMethod(String ratingMethod) {
        this.ratingMethod = ratingMethod;
    }

}
