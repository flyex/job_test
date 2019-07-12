package com.flyex.go.user.action;

import com.flyex.go.user.bean.Student;
import com.flyex.go.user.service.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentAction extends ActionSupport implements ModelDriven<Student> {
    private Student student = new Student();
    public Student getModel(){
        return student;
    }

    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String loginPage(){
        return "loginPage";
    }

    public String login() throws Exception{
        Student existStudent = studentService.findStudent(student);
        /**SimpleDateFormat smf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = smf.parse(smf.format(existStudent.getBirth().getTime()));
        Timestamp date1 = new Timestamp(date.getTime());
        existStudent.setBirth(date1);**/
        if (existStudent!=null){

            ServletActionContext.getRequest().getSession().setAttribute("existStudent",existStudent);
            return "loginSuccess";
        }else {
            this.addActionError("登陆失败，账号或密码错误，或账号没有激活，或没有注册");
            return LOGIN;
        }
    }
}
