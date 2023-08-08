package com.example.exam.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exam.entity.Answer;
import com.example.exam.entity.Paper;
import com.example.exam.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerMapper extends BaseMapper<Answer> {

}
