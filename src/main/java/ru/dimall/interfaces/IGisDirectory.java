package ru.dimall.interfaces;

import java.util.Map;

/**
 *
 * @param <T>
 */
public interface IGisDirectory<T> {

    /**
     *
     * @return
     */
    IHttpUrlConnection getConnection();

    /**
     *
     * @param connection
     */
    void setConnection(IHttpUrlConnection connection);

    /**
     *
     * @return
     */

    IFirmList<T> handle(Map<String,String> parameters);

}
