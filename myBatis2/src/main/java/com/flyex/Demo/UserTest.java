package com.flyex.Demo;

import com.flyex.bean.User;
import com.flyex.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class UserTest {
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
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setId(1);
            user.setUsername("小米");
            user.setAge(18);
            userMapper.add(user);

            sqlSession.commit();

            //List<User> list = userMapper.findUserAll();
            //System.out.println(list);

            List<User> list = userMapper.findByName("小米");
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

        sqlSession.close();
    }
}
