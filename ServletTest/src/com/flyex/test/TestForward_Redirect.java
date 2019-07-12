package com.flyex.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestForward_Redirect")
public class TestForward_Redirect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name="123";
        //request.setAttribute("name",name);
        //request.getRequestDispatcher("test2.jsp").forward(request,response);
        response.sendRedirect("test2.jsp?name="+name);
    }
}
