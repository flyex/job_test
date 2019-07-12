package com.flyex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadController {
    @RequestMapping("/index")
    public String index(){
        return "index2";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws Exception{
        if(!file.isEmpty()){
            String contextPath = request.getContextPath();
            String servletPath = request.getServletPath();
            String scheme = request.getScheme();

            String storePath ="D:\\project\\taotaoparent\\taotao-manager\\taotao-web\\src\\main\\webapp\\store";
            String fileName = file.getOriginalFilename();

            File filePath = new File(storePath,fileName);
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdir();
            }

            file.transferTo(new File(storePath+File.separator+fileName));
            return "test";
        }
        return null;
    }
}
