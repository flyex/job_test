package com.flyex.Test;


import com.flyex.web.HomeController;
import org.junit.Test;
import org.junit.runner.Request;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {
   /** @Test
    public void testHomePage() throws Exception{

        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();
       // mockMvc.perform().andExpect(view().name("home"));
    }**/
}
