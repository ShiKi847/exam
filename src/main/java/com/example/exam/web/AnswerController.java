package com.example.exam.web;

import com.example.exam.entity.Answer;
import com.example.exam.pojo.JsonResult;
import com.example.exam.service.AnswerService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
*答题
 */
@Controller
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @PostMapping("/clk")
    @ResponseBody
    @RequiresRoles("USER")
    public JsonResult<?> clk(Answer answer){
        return answerService.clk(answer);

    }
}
