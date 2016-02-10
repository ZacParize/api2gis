package ru.dimall.interfaces;

import java.util.Map;

/**
 * data of application launching
 */
public interface IGisAppData {

    /**
     * get application data
     * @return application data map
     */
    Map<String, String> getData();

    /**
     * set application data
     * @param data
     */
    void setData(Map<String, String> data);

}
