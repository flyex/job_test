package com.flyex.action;

import com.flyex.model.Student;
import com.flyex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

        public String updateStudent(Student student, HttpServletRequest request) {
                if (studentService.update(student)) {
                        student = studentService.findById(student.getId());
                        request.setAttribute("student", student);
                        return "redirect:/student/getAllStudent";
                } else{
                        return "/error";
                }
        }

        public void delStudent(int id, HttpServletRequest request, HttpServletResponse response) {
                String result = "{\"result\":\"error\"}";
                if (studentService.delete(id)) {
                        result = "{\"result\":\"success\"}";
                }
                response.setContentType("application/json");
                try {
                        PrintWriter out = response.getWriter();
                        out.write(result);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public String getStudent(int id, HttpServletRequest request) {
                request.setAttribute("student", studentService.findById(id));
                return "/editStudent";
        }
        public String getAllStudent(HttpServletRequest request) {
                List<Student> findAll = studentService.findAll();
                request.setAttribute("studentList", findAll);
                return "/allStudent";
        }
}
