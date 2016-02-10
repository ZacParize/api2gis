package ru.dimall.interfaces;

/**
 * class of cooperation with Flamp catalog
 * @param <T>
 */
public interface IGisFlamp<T> {

    /**
     * get connection
     * @return connection
     */
    IHttpUrlConnection getConnection();

    /**
     * set connection
     * @param connection
     */
    void setConnection(IHttpUrlConnection connection);

    /**
     * fill firms rating
     * @param firms
     */
    void fillRating(IFirmList<T> firms);

}
