package com.example.exam.service;

import com.example.exam.entity.Answer;
import com.example.exam.pojo.JsonResult;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {
    JsonResult<?> clk(Answer answer);
}
