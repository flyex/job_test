package com.flyex.controller;

import com.flyex.pojo.Student;
import com.flyex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/show")
    @ResponseBody
    public List<Student> findAll(){

        return studentService.findAll();
    }
    @RequestMapping("/show/{id}")
    @ResponseBody
    public Student findById(@PathVariable int id){
        return studentService.findById(id);
    }

    @RequestMapping("/index/{page}")
    @ResponseBody
    public List<Student> showByPage(@PathVariable int page){
        return studentService.showByPage(page);
    }



    @RequestMapping("/div")
    public String div(){
        return "div";
    }
    @RequestMapping("/frame")
    public String frame(){
        return "/frame/frame";
    }
}
