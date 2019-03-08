package com.flyex.services;


public class RequestService {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printName(){
        System.out.println("我请求的姓名："+this.name);
    }
    public void printURL(){
        System.out.println("我请求的网址："+this.url);
    }
    public void printThrowException(){
        throw new IllegalArgumentException();
    }
}
