package com.example.exam.web;

import com.example.exam.entity.Paper;
import com.example.exam.service.PaperService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 试卷
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;
    //添加试卷
    @GetMapping("/addPaper")
    @RequiresRoles("ADMIN")
    public String addPaper(){
        return "addPaper";
    }
    //添加试卷
    @PostMapping("/addPaper")
    @RequiresRoles("ADMIN")
    public String addPaper(Paper paper, Model model){
        boolean flag=paperService.addPaper(paper);
        if(flag) {return "redirect:/index";}
        model.addAttribute("tip","卷名或密码错误");
        return "addPaper";
    }
    //管理试卷
    @GetMapping("/managerPaper")
    @RequiresRoles("ADMIN")
    public String managerPaper(){
        return "managerPaper";
    }
}
