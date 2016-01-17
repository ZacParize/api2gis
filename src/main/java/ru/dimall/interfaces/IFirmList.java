package ru.dimall.interfaces;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface IFirmList<T> {

    List<T> getFirms();

    void setFirms(List<T> firms);

    String toString(int count);

}
