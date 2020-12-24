package com.flyex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class UserController {

    @RequestMapping("/host")
    @ResponseBody
    public String host(){

        String host = null;
        try{
            host = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }

        return host;
    }
}
