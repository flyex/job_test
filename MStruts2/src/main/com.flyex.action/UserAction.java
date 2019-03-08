import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class UserAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
     public String save(){
         return SUCCESS;
     }
     public String login() {
         try {
             HttpServletRequest request = ServletActionContext.getRequest();
             HttpServletResponse response = ServletActionContext.getResponse();
             request.setCharacterEncoding("UTF-8");
             response.setContentType("text/html;charset=utf-8");
             String password = request.getParameter("password");
             String username = request.getParameter("username");
             System.out.println("输入的用户和密码是："+username+password);
             if("admin".equals(username)&&"123456".equals(password)){
                 return SUCCESS;
             }else {
                 return "login";
             }
         }catch (UnsupportedEncodingException e){
             e.printStackTrace();
         }
         return SUCCESS;
     }

}
