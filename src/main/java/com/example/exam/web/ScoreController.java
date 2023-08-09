package com.example.exam.web;

import com.example.exam.pojo.JsonResult;
import com.example.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("submit/{paId}")
    @ResponseBody
    public JsonResult<?> submit(@PathVariable Integer paId){
        return scoreService.submit(paId);
    }

}

