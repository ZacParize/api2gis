package ru.dimall.implementations;

import ru.dimall.interfaces.IFirmList;

import java.io.Serializable;
import java.util.*;

/**
 *
 * POJO
 * list of <Firmobject>
 *
 * @author  franco
 * @see IFirmList
 * @see Firmobject
 * @see Serializable
 */
public class Firmlist implements IFirmList<Firmobject>, Serializable {

    private List<Firmobject> firms = new ArrayList();

    /**
     *
     * @return
     */
    @Override
    public List<Firmobject> getFirms() {
        return firms;
    }

    /**
     *
     * @param firms
     */
    @Override
    public void setFirms(List<Firmobject> firms) {
        this.firms = firms;
    }

    /**
     *
     */
    @Override
    public void sort() {
        Collections.sort(this.getFirms(), new Comparator<Firmobject>() {
            public int compare(Firmobject o1, Firmobject o2) {
                if (o1.getRating() == o2.getRating())
                    return 0;
                else
                    return o1.getRating() < o2.getRating() ? 1 : -1;
            }
        });
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        List<String> result = new ArrayList();
        for (Firmobject firm: this.getFirms()) result.add(firm.toString());
        return result.toString();
    }

}
