package com.example.exam.web;

import com.example.exam.entity.Paper;
import com.example.exam.entity.User;
import com.example.exam.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

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

    @RequestMapping("/register")
    public String register(){return "register";}

    @PostMapping("/register")
    public String register(User user,Model model){
        //后端进行校验
        if(!user.getUsrName().matches("^[\\w\\u2E80-\\u9FFF]{2,16}$")){
            model.addAttribute("tip","别胡来");
            return "register";
        }
        if(!user.getUsrAccount().matches("^\\w{2,16}$")){
            model.addAttribute("tip","别胡来");
            return "register";
        }
        if(!user.getUsrPassword().matches("^\\w{2,16}$")){
            model.addAttribute("tip","别胡来");
            return "register";
        }

        boolean flag =userService.register(user);
        if(flag) {return "redirect:/index";}
        model.addAttribute("tip","账号或密码错误");
        return "register";
    }
    //管理用户
    @RequestMapping("/managerUser")
    @RequiresRoles("SYSTEM")
    public String managerUser(Model model){
        List<User> userList = userService.managerUser();
        model.addAttribute("userList",userList);
        return "managerUser";
    }
    //管理用户的双击修改密码功能
    @PostMapping("/updateUsrPassword")
    @RequiresRoles("SYSTEM")
    @ResponseBody
    public boolean updateUsrPassword(User user){
        return userService.updateUsrPassword(user);
    }


}
