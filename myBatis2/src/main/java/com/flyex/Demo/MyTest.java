package com.flyex.Demo;

import com.flyex.bean.My;
import com.flyex.mapper.MyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyTest {
    static Reader reader;
    static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyMapper myMapper = sqlSession.getMapper(MyMapper.class);
        My my = new My();
        my.setId(1);
        my.setName('1');
        my.setAge(16);
        myMapper.add(my);
        sqlSession.commit();





        sqlSession.close();
    }

}
