package com.XSmile2008.helloworldandroid.app;

/**
 * Created by vladstarikov on 18.01.15.
 */
public class PhoneModel {

    private long id;
    private String name;
    private long price;

    public PhoneModel(long id, String name, long price){
        this.id = id;
        this.name = name;
        this.price = price;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
