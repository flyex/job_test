package com.flyex.springjdbc;

import com.flyex.bean.Department;
import com.flyex.dao.DepartmentDAO;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainDemo {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        DepartmentDAO deptDao = (DepartmentDAO)context.getBean("departmentDAO");
        List<Department> depts = deptDao.queryDepartment();
        for(Department department:depts){
            System.out.print("dept ID:"+department.getDeptId()+"   ");
            System.out.print("dept No:"+department.getDeptNo()+"   ");
            System.out.println("dept Name;"+department.getDeptName());
        }
    }
}
