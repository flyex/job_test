package com.flyex.demo.staticPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StaticController {
    @RequestMapping(value = "/index2" ,method = RequestMethod.GET)
    public String index(){
        return "index2";
    }

    @RequestMapping(value = "/staticPage" , method = RequestMethod.GET)
    public String rediret(){
        return "redirect:d:/project/springMVC/web/WEB-INF/page/staticPage/finnal.html";
    }
}
