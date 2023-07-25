package com.example.exam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * homepage
     * @return
     */
    @RequestMapping({"/","index"})
    public String index(){return "index";}
}
