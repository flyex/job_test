package com.flyex.pojo;

import java.sql.Date;

public class Students {
    private int id;
    private String name;
    private Date birth;

    public Students(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "id:"+id+"    name:"+name+"      birth:"+birth;
    }
}
