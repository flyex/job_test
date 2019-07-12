package com.flyex.bo;


import com.flyex.bean.User2;
import com.flyex.dao.StudentDao;
import com.flyex.utils.PageBean;

import java.util.List;

public class StudentService {
   private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void add(User2 user2){
        studentDao.add(user2);
    }

    public List finBy(int paiming, String username){
        return studentDao.findBy(paiming,username);
    }

    public User2 findByUsername(String username){
       return studentDao.findByUsername(username);
    }
    public User2 login(User2 user2){
        return studentDao.login(user2);
    }
    //显示所有用户
    public List<User2> findAll(){
        return studentDao.findAll();
    }
    //分页显示
    public PageBean<User2> findAllPage(int page){
        PageBean<User2> pageBean = new PageBean<User2>();
        pageBean.setPage(page);
        int limit = 3;
        pageBean.setLimit(limit);
        int totalCount = studentDao.findCountId();
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount%limit==0){
            totalPage = totalCount/limit;
        }else {
            totalPage = totalCount/limit+1;
        }
        pageBean.setTotalPage(totalPage);
        int begin = (page-1)*limit;
        List<User2> list = studentDao.findAllPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }
}
