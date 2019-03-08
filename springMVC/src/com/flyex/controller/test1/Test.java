package com.flyex.controller.test1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class Test {
    @RequestMapping("/param")
    public ModelAndView getParam(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName+"........."+password);
        return null;
    }


    /**public ModelAndView getParam(String userName,String password){
        System.out.println(userName+"........."+password);
        return null;
    }**/


    public ModelAndView getParam(@RequestParam("userName")String name,@RequestParam("password")String password){
        System.out.println(name+",,,,,,"+password);
        return null;
    }
}
