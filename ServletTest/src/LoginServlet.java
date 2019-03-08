import java.io.IOException;
import java.io.Writer;
import javax.*;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //向服务器发送请求获取到参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+"--"+password);

        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        Writer out=response.getWriter();
        out.write("用户名："+username);
        out.write("密码："+password);
        out.flush();
        out.close();
    }
}
