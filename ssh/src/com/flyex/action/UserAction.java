package com.flyex.action;

import com.flyex.bean.User2;
import com.flyex.bo.StudentService;
import com.flyex.dao.StudentDao;
import com.flyex.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UserAction extends ActionSupport {
    private StudentService studentService;

    private String test1;
    //接受页面的page
    private int page;

    public void setPage(int page) {
        this.page = page;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String regist(){
        return "regist";
    }

    public String add() throws Exception{
        System.out.println(test1);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String paiming = request.getParameter("paiming");
        if(username==null||paiming==null){
            return "regist";
        }else {
            User2 user2 = new User2(Integer.parseInt(paiming),username);
            System.out.println(user2.getPaiming());
            studentService.add(user2);
            return "success";
        }
        //System.out.println(username+paiming);
    }

    public String login(){
            return "login" ;
    }

    public String loginCheck(){
        HttpServletRequest request = ServletActionContext.getRequest();
        int paiming =Integer.parseInt( request.getParameter("paiming"));
        String username = request.getParameter("username");
        System.out.println(paiming+username);
        //System.out.println((studentService.finBy(paiming,user)).get(0));
        List<Integer> list = studentService.finBy(paiming,username);
        //System.out.println(list.get(0));
        if (list!=null){
            ServletActionContext.getRequest().getSession().setAttribute("username",username );
            return "loginSuccess";
        }else if (list==null){
            this.addActionError("账户或密码错误请重新登录");
            return LOGIN;
        }
        return null;
    }

    public String findByUsername() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String username = request.getParameter("username");
            System.out.println("输入的用户是："+username);
            User2 existUser = studentService.findByUsername(username);
            //System.out.println("根据搜到的用户名是："+existUser.getUsername());
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF-8");
            if (existUser!=null){
                response.getWriter().print("<font color='red'>用户名已存在</font>");
                System.out.println("不可以使用");
            }else {
                System.out.println("可以使用");
                response.getWriter().print("<font color = 'green'>用户名可以使用</font>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return NONE;
    }

    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();
        return "quit";
    }

    /**public String regist(){
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response =ServletActionContext.getResponse();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            String name = request.getParameter("username");
            String ranking = request.getParameter("paiming");
            if((name!=null)&&(ranking!=null)){
                User2 user2 = new User2(Integer.parseInt(ranking),name);
                studentDao.add(user2);
                return SUCCESS;
            }else {
                return LOGIN;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String login(){
        return null;
    }**/

    //显示所有用户
    public String showAll(){
        List<User2> uLists = studentService.findAll();
        ActionContext.getContext().getValueStack().set("uList",uLists);
        return "show";
    }
    //分页显示
    public String showPage(){
        PageBean<User2> pageBean = studentService.findAllPage(page);
        System.out.println(pageBean.getList());
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "showPage";
    }
}
