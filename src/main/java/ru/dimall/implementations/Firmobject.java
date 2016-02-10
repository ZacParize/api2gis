package ru.dimall.implementations;

import java.io.Serializable;

/**
 *
 * POJO
 * store of information about company
 * @author  franco
 * @see Serializable
 */
public class Firmobject implements Serializable {

    private static final long serialVersionUID = 1000000000000000003L;
    private long id;
    private String name;
    private String address;
    private double rating;
    private String hash;

    public Firmobject(long id,String name,String address,int rating,String hash) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.hash = hash;
    }

    /**
     * get id
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get rating
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * set rating
     * @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * get hash
     * @return hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * set hash
     * @param hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Object to string
     * @return string view of the object
     */
    @Override
    public String toString() {
        return "{\"name\":\"" + this.name + "\",\"address\":\"" + this.address + "\",\"rating\":\"" + String.valueOf(this.rating) + "\"}";
    }

}
