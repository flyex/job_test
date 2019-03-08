package com.flyex.controller.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Upload {
    @RequestMapping("/upload")
    public void upload(@RequestParam("picture")MultipartFile picture) throws Exception{
        System.out.println(picture.getOriginalFilename());
    }

    @RequestMapping("/test3")
    public ModelAndView jump(){
        return new ModelAndView("upload");
    }
}
