package com.flyex.utils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>> {
	
	private String hql;
	private int startIndex;
	private int pageSize;
	

	public PageHibernateCallback(String hql, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}



	public List<T> doInHibernate(Session session) throws HibernateException
            {
		//1 执行hql语句
		Query query = session.createQuery(hql);
		//2 实际参数
		//3 分页
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		
		return query.list();
	}

}
