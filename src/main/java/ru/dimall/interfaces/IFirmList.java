package ru.dimall.interfaces;

import java.util.List;

/**
 * list of organizations
 * @param <T> organization itself
 */
public interface IFirmList<T> {

    /**
     * get organization list
     * @return organization list
     */
    List<T> getFirms();

    /**
     * set list of organizations
     * @param firms list of organizations
     */
    void setFirms(List<T> firms);

    /**
     * view object to string
     * @param count
     * @return string view of object
     */
    String toString(int count);

}
