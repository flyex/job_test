package com.test.wuzhong.vo;

import com.test.kelei.vo.Kelei;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Wuzhong {
    private int wid;
    private String wname;

    private Set<Kelei> keleis = new HashSet<Kelei>();

    public Set<Kelei> getKeleis() {
        return keleis;
    }

    public void setKeleis(Set<Kelei> keleis) {
        this.keleis = keleis;
    }

    @Id
    @Column(name = "wid", nullable = false)
    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "wname", nullable = false, length = 255)
    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wuzhong wuzhong = (Wuzhong) o;
        return wid == wuzhong.wid &&
                Objects.equals(wname, wuzhong.wname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wid, wname);
    }

    @Override
    public String toString() {
        return "我的物种是:"+wname;
    }
}
