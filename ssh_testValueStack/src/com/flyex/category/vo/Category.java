package com.flyex.category.vo;

import com.flyex.categorySecond.vo.CategorySecond;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category {
    private int cid;
    private String cname;

    private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

    public Set<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    @Id
    @Column(name = "cid", nullable = false)
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "cname", nullable = true, length = 255)
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return cid == category.cid &&
                Objects.equals(cname, category.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname);
    }

    @Override
    public String toString() {
        return "一级分类名称："+cname+"下面的二级分类："+categorySeconds;
    }
}
