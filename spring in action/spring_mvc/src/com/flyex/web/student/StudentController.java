package com.flyex.web.student;

import com.flyex.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register","command",new Student());
    }
    @RequestMapping(value = "/addstudent",method = RequestMethod.POST)
    public String add(@Valid Student student, Errors errors, ModelMap modelMap){
        if (errors.hasErrors()){
            return "redirect:/register";
        }
        modelMap.addAttribute("name",student.getName());
        modelMap.addAttribute("address",student.getAddress());
        modelMap.addAttribute("age",student.getAge());
        return "/result";
    }
}
