package com.example.exam.service;

import com.example.exam.entity.Score;
import com.example.exam.pojo.JsonResult;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ScoreService {

    JsonResult<?> submit(Integer paId);

    List<Score> selectList();
}
