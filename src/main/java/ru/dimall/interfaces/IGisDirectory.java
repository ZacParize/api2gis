package ru.dimall.interfaces;

import java.util.Map;

/**
 * class of cooperation with 2GIS catalog
 * @param <T> organization
 */
public interface IGisDirectory<T> {

    /**
     * get connection to
     * @return connection
     */
    IHttpUrlConnection getConnection();

    /**
     * set connection to
     * @param connection
     */
    void setConnection(IHttpUrlConnection connection);

    /**
     * commit request particular to 2GIS catalog
     * @return list of organizations
     */
    IFirmList<T> handle(Map<String,String> parameters);

}
