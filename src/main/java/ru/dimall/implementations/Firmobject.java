package ru.dimall.implementations;

import java.io.Serializable;

/**
 *
 * POJO
 * consists information about company
 *
 * @author  franco
 * @see Serializable
 */

public class Firmobject implements Serializable {

    private static final long serialVersionUID = 1000000000000000003L;
    private long id;
    private String name;
    private String address;
    private int rating;
    private String hash;

    public Firmobject(long id,String name,String address,int rating,String hash) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.hash = hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + this.name + "\",\"address\":\"" + this.address + "\",\"rating\":\"" + String.valueOf(this.rating) + "\"}";
    }

}
