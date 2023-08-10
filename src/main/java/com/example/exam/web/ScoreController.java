package com.example.exam.web;

import com.example.exam.entity.Score;
import com.example.exam.pojo.JsonResult;
import com.example.exam.service.ScoreService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    //提交成绩
    @GetMapping("submit/{paId}")
    @ResponseBody
    @RequiresRoles("USER")
    public JsonResult<?> submit(@PathVariable Integer paId){
        return scoreService.submit(paId);
    }

    //我的成绩
    @RequiresRoles("USER")
    @GetMapping("/myScore")
    public String myScore(Model model){
        List<Score> scoreList = scoreService.selectList();
        model.addAttribute("scoreList",scoreList);
        return  "myScore";
    }

}

