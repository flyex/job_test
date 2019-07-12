package com.flyex.web.show_jsp_c_foreach;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
@Controller
public class Items_list {
    List<String> list = new ArrayList<String>();

    @RequestMapping(value = "/showItems",method = RequestMethod.GET)
    public String showItems(Model model){
        list.add("asd");
        list.add("asdfdsf");
        list.add("34rd");
        list.add("34rd");
        list.add("34rd");
        list.add("34rd");
        list.add("34rd");
        model.addAttribute("list",list);
        return "showItems";
    }
}
