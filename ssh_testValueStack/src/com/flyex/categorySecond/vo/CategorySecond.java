package com.flyex.categorySecond.vo;

import com.flyex.category.vo.Category;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CategorySecond {
    private int csid;
    private String csname;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Id
    @Column(name = "csid", nullable = false)
    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    @Basic
    @Column(name = "csname", nullable = true, length = 255)
    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorySecond that = (CategorySecond) o;
        return csid == that.csid &&
                Objects.equals(csname, that.csname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csid, csname);
    }

    @Override
    public String toString() {
        return "所属种类"+category.getCname()+"***我的名称"+csname;
    }
}
