package com.test2.grade.vo;

import com.test2.user2.vo.User2;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Grade {
    private int gid;
    private String username;
    private Integer math;
    private Integer english;

    private User2 user2;

    public User2 getUser2() {
        return user2;
    }

    public void setUser2(User2 user2) {
        this.user2 = user2;
    }

    @Id
    @Column(name = "gid", nullable = false)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "math", nullable = true)
    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    @Basic
    @Column(name = "english", nullable = true)
    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return gid == grade.gid &&
                Objects.equals(username, grade.username) &&
                Objects.equals(math, grade.math) &&
                Objects.equals(english, grade.english);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, username, math, english);
    }

    @Override
    public String toString() {
        return username+"的数学成绩是："+math+",------英语成绩是："+english;
    }
}
