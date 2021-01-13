package com.xxxx;

public class MyBean {
    private String id;
    private String  Clazz;

    public MyBean(String id, String clazz) {
        this.id = id;
        Clazz = clazz;
    }
    public MyBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return Clazz;
    }

    public void setClazz(String clazz) {
        Clazz = clazz;
    }
}
