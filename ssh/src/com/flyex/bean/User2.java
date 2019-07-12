package com.flyex.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User2 {
    private int id;
    private int paiming;
    private String username;

    public User2(){}
    public User2(int paiming, String username) {
        //this.id = id;
        this.paiming = paiming;
        this.username = username;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paiming", nullable = true)
    public int getPaiming() {
        return paiming;
    }

    public void setPaiming(int paiming) {
        this.paiming = paiming;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 10)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User2 user2 = (User2) o;
        return id == user2.id &&
                Objects.equals(paiming, user2.paiming) &&
                Objects.equals(username, user2.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paiming, username);
    }

    @Override
    public String toString() {
        return username;
    }
}
