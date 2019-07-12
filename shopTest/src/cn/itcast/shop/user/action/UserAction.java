package cn.itcast.shop.user.action;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getModel(){
        return user;
    }


    //接收验证码
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }


    public String registPage(){
        return "registPage";
    }

    public String findByName() throws IOException {
        User existUser = userService.findByUsername(user.getUsername());

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");

        if(existUser!=null){
            //用户名已存在
            response.getWriter().print("<font color='red'>用户名已存在</font>");
        }else {
            response.getWriter().print("<font color = 'green'>用户名可以使用</font>");
        }

        return NONE;
    }

    public String regist(){

        String code1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
        if(!checkcode.equalsIgnoreCase(code1)){
            this.addActionError("验证码输入错误");
            return "checkcodeFail";
        }


        userService.save(user);
        this.addActionMessage("注册成功！请去邮箱激活");


        return "msg";

    }

    public String active(){
        // 根据激活码查询用户:   注意这点回忆  user.getCode()不能理解
        User existUser = userService.findByCode(user.getCode());
        // 判断
        if (existUser == null) {
            // 激活码错误的
            this.addActionMessage("激活失败:激活码错误!");
        } else {
            // 激活成功
            // 修改用户的状态
            existUser.setState(1);
            existUser.setCode(null);
            userService.update(existUser);
            this.addActionMessage("激活成功:请去登录!");
        }
        return "msg";
    }

    public String loginPage(){

        return "loginPage";
    }

    public String login(){
        User existUser = userService.login(user);
        if (existUser==null){
            this.addActionError("登陆失败，用户名或者密码错误，或者用户未激活");
            return LOGIN;
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginSuccess";
        }
    }

    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();
        return "quit";
    }
}
