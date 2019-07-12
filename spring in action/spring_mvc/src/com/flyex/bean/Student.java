package com.flyex.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {

    @NotNull(message = "姓名不能为空")
            @Size(max =6,min = 1)
    String name;

    @Size(max =6,min = 1)
    String address;

    @Size(max =6,min = 1)
    int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
