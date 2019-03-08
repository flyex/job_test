package com.flyex.controller.test2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Test2 {
    @RequestMapping("/value")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("message","你很溜！");
        return new ModelAndView("test2");
    }
}
