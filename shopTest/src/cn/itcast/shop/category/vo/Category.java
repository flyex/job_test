package cn.itcast.shop.category.vo;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category implements Serializable {
    private Integer cid;
    private String cname;

    //一级分类查询二级分类集合 注意set集合是无序的 每次分类排序会变 在hbm里set属性配置order by即可
    private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

    public Set<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }


    @Id
    @Column(name = "cid", nullable = true)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
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
        return Objects.equals(cid, category.cid) &&
                Objects.equals(cname, category.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname);
    }
}
