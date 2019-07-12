import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentsController {
    @RequestMapping(value = "/student" ,method = RequestMethod.GET)
    public ModelAndView student(){
        Students students = new Students();
        ModelAndView modelAndView = new ModelAndView("student","command",students);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public String add(@ModelAttribute("SpringWeb") Students students, ModelMap modelMap){
        modelMap.addAttribute("name",students.getName());
        modelMap.addAttribute("Id",students.getId());
        return "studentList";
    }
}
