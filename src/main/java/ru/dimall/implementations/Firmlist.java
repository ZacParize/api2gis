package ru.dimall.implementations;

import ru.dimall.interfaces.IFirmList;
import java.io.Serializable;
import java.util.*;

/**
 * POJO
 * list of organizations
 * @author  franco
 * @see IFirmList
 * @see Firmobject
 * @see Serializable
 */
public class Firmlist implements IFirmList<Firmobject>, Serializable {

    private static final long serialVersionUID = 1000000000000000008L;
    private List<Firmobject> firms = new ArrayList();

    /**
     * get list of organizations
     * @return list of organizations
     */
    @Override
    public List<Firmobject> getFirms() {
        return firms;
    }

    /**
     * set list of organizations
     * @param firms list of organizations
     */
    @Override
    public void setFirms(List<Firmobject> firms) {
        this.firms = firms;
    }

    /**
     * get ordered list of organizations
     * @return set of organizations
     */
    private Set<Firmobject> getSetInstance() {
        /*below is a object which always could help to order your list of firms by RATING AND AFTER ALL ALPHABET*/
        return new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer prefix1 = ((Firmobject) o2).getName().compareTo(((Firmobject) o1).getName());
                Integer prefix2 = ((Firmobject) o1).getName().compareTo(((Firmobject) o2).getName());
                String hash1 = String.valueOf(((Firmobject) o1).getRating()) + prefix1.toString() + ((Firmobject) o1).getHash();
                String hash2 = String.valueOf(((Firmobject) o2).getRating()) + prefix2.toString() + ((Firmobject) o2).getHash();
                return hash2.compareTo(hash1);
            }
        });
    }

    /**
     * Collaborate list to set
     * @return set of organizations
     */
    private Set<Firmobject> toSet() {
        /*add a description*/
        Set<Firmobject> set = this.getSetInstance();
        set.addAll(this.getFirms());
        return set;
    }

    /**
     * Object to string
     * @return String
     */
    @Override
    public String toString() {
        return this.toSet().toString();
    }

    /**
     * Object to string
     * @param count
     * @return String
     */
    @Override
    public String toString(int count) {
        Set<Firmobject> oldSet = this.toSet();
        Set<Firmobject> newSet = this.getSetInstance();
        Iterator<Firmobject> iterator = oldSet.iterator();
        int idx = 0;
        while (iterator.hasNext() && idx < count) {
            newSet.add(iterator.next());
            idx++;
        }
        return newSet.toString();
    }

}
