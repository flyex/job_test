package com.flyex.action;

import com.flyex.model.Student;
import com.flyex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
public class StudentAction {
        @Autowired
        private StudentService studentService;

        public String toAddStudent(HttpServletRequest httpServletRequest){
                return "/addStudent";
        }
        @RequestMapping("/addStudent")
        public String addStudent(Student student,HttpServletRequest httpServletRequest){
                studentService.save(student);
                return "redirect:/student/getAllStudent";
        }

        public String updateUser(Student student, HttpServletRequest request) {
                if (studentService.update(student)) {
                        student = studentService.findById(student.getId());
                        request.setAttribute("student", student);
                        return "redirect:/student/getAllStudent";
                } else{
                        return "/error";
                }
        }
}
