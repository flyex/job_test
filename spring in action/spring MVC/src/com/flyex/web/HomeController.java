package com.flyex.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller     //声明为控制器
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
