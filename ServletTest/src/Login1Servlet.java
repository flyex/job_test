import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login1Servlet")
public class Login1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>用户登录</TITLE></HEAD>");
        out.println("  <BODY>");
        out.println("<h1>用户登录</h1>");
        out.println("<form action='/ServletTest/loginCL1Servlet' method='post'>");
        out.println("用户名：<input type='text' name='username'/><br/>");
        out.println("密    码：<input type='password' name='password'/><br/>");
        out.print("<input type='submit' value='登录'/>");
        out.println("<input type='reset' value='重置'/><br/>");
        out.println("</form>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

this.doPost(request,response);
    }
}
