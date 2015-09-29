package ru.dimall.interfaces;

/**
 *
 * @param <T>
 */
public interface IGisFlamp<T> {

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
     * @param firms
     */
    void fillRating(IFirmList<T> firms);

}
