package com.flyex.action;

import com.flyex.bean.User2;
import com.flyex.bo.StudentService;
import com.mysql.jdbc.log.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

public class User2Action extends ActionSupport implements ModelDriven<User2> {
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private User2 user2 = new User2();

    public User2 getModel(){
        return user2;
    }


    public String loginPage(){
        return "loginPage";
    }

    public String login(){
        User2 existUser = studentService.login(user2);
        if (existUser!=null){
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginSuccess";
        }else {
            this.addActionError("用户名或密码错误");
            return LOGIN;
        }
    }

    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();
        return "quit";
    }
    public String registPage(){
        return "registPage";
    }
    public String regist(){
        studentService.add(user2);
        this.addActionMessage("注册成功");
        return "msg";
    }



}
