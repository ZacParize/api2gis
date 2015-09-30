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
     * @return
     */
    @Override
    public String toString() {
        Set<Firmobject> set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String hash1 = String.valueOf(((Firmobject) o1).getRating()) + ((Firmobject) o1).getHash();
                String hash2 = String.valueOf(((Firmobject) o2).getRating()) + ((Firmobject) o2).getHash();
                return hash2.compareTo(hash1);
            }
        });
        set.addAll(this.getFirms());
        return set.toString();
    }

}
