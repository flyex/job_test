package com.test.kelei.vo;

import com.test.wuzhong.vo.Wuzhong;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Kelei {
    private int kid;
    private String kname;

    private Wuzhong wuzhong;

    public Wuzhong getWuzhong() {
        return wuzhong;
    }

    public void setWuzhong(Wuzhong wuzhong) {
        this.wuzhong = wuzhong;
    }

    @Id
    @Column(name = "kid", nullable = false)
    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    @Basic
    @Column(name = "kname", nullable = false, length = 255)
    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kelei kelei = (Kelei) o;
        return kid == kelei.kid &&
                Objects.equals(kname, kelei.kname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kid, kname);
    }

    @Override
    public String toString() {
        return "我的科类是："+kname;//+",我属于:"+wuzhong.getWname();
    }
}
