package com.flyex.category.dao;

import com.flyex.category.vo.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDao extends HibernateDaoSupport {

    @Autowired
    public void setSuperSessionFactory(SessionFactory superSessionFactory){
        super.setSessionFactory(superSessionFactory);
    }

    public List<Category> findAll(){
        String hql = "from Category";
        List<Category> list = (List<Category>)this.getHibernateTemplate().find(hql);
        return list;
    }

}
