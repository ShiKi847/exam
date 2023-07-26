package com.example.exam.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/login")
    public String login(){return "login";}
    @PostMapping("/login")
    public String login(String usrAccount, String usrPassword, Model model){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(usrAccount,usrPassword));
            return "redirect:/index";
        } catch (AuthenticationException e) {
            model.addAttribute("tip","账号或密码错误");
            return "login";
        }

    }

    @RequestMapping("register")
    public String register(){return "register";}
}
