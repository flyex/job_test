package com.flyex.web.show_jsp_c_foreach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.print.DocFlavor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Controller
@Component
public class Items_list2 {
    @NotNull
            @Size(min = 1,max = 10)
    List<String> list = new ArrayList<String>();

    public void setList(List list){
        this.list = list;
    }
    @RequestMapping(value = "/go",method = RequestMethod.GET)
    public String go(Model model){
        model.addAttribute("list2",list);
        return "go";
    }
}
