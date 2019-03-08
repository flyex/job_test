package com.flyex.controller;

import com.flyex.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        User user = new User();
        user.setFavoriteFrameworks((new String []{ }));
        user.setGender("M");
        ModelAndView modelAndView = new ModelAndView("user", "command", user);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody @Validated @ModelAttribute("SpringWeb")User user,
                          ModelMap model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("receivePaper", user.isReceivePaper());
        model.addAttribute("favoriteFrameworks", user.getFavoriteFrameworks());
        model.addAttribute("gender",user.getGender());
        model.addAttribute("oneChose",user.getOneChose());
        model.addAttribute("country",user.getCountry());
        model.addAttribute("skills",user.getSkills());
        model.addAttribute("hiddenId",user.getHiddenId());
        return "userlist";
    }

    @ModelAttribute("webFrameworkList")
    public List<String> getWebFrameworkList()
    {
        List<String> webFrameworkList = new ArrayList<String>();
        webFrameworkList.add("Spring MVC");
        webFrameworkList.add("Spring Boot");
        webFrameworkList.add("Struts 2");
        webFrameworkList.add("Apache Hadoop");
        return webFrameworkList;
    }

    @ModelAttribute("oneList")
    public List<String> getOneList(){
        List<String> oneList = new ArrayList<String>();
        oneList.add("跑步");
        oneList.add("登山");
        oneList.add("骑车");
        oneList.add("慢跑");

        return oneList;
    }

    @ModelAttribute("countryList")
    public Map<String,String> getCountryList(){
        Map<String,String> countryList = new HashMap<String, String>();
        countryList.put("us","美国");
        countryList.put("china","中国");
        countryList.put("japan","日本");
        return countryList;
    }

    @ModelAttribute("skillsList")
    public Map<String, String> getSkillsList()
    {
        Map<String, String> skillList = new HashMap<String, String>();
        skillList.put("Hibernate", "Hibernate");
        skillList.put("Spring", "Spring");
        skillList.put("Apache Hadoop", "Apache Hadoop");
        skillList.put("Struts", "Struts");
        return skillList;
    }
}
