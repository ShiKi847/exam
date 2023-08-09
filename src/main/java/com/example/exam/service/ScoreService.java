package com.example.exam.service;

import com.example.exam.pojo.JsonResult;
import org.springframework.stereotype.Service;


@Service
public interface ScoreService {

    JsonResult<?> submit(Integer paId);
}
