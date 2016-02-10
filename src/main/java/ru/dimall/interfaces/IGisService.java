package ru.dimall.interfaces;

import java.util.Map;

/**
 * Whole info about organization
 */
public interface IGisService {

    /**
     * get application data
     * @return application data
     */
    IGisAppData getAppData();

    /**
     * return filled list of organizations
     * @param requestParameters request parameters
     * @return list of organizations
     */
    /*String*/IFirmList<?> getInfo(Map<String,String> requestParameters);

}
