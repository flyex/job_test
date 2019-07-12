package com.flyex.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home")    //访问url则为 /home/hp
public class HomeWithParam {

    @RequestMapping(value = "/hp",method = RequestMethod.GET)
    public String home_param(Model model){
        model.addAttribute("name","小李");
        model.addAttribute("address","长江");
        return "home_param";
    }
}
