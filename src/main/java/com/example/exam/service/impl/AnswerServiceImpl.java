package com.example.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exam.dao.AnswerMapper;
import com.example.exam.entity.Answer;
import com.example.exam.entity.User;
import com.example.exam.pojo.JsonResult;
import com.example.exam.service.AnswerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Override
    public JsonResult<?> clk(Answer answer) {
        //先查询是否已经选过了答案
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Answer> qw = new QueryWrapper<>();
        qw.eq("ans_usr_id",user.getUsrId());
        qw.eq("ans_pa_id",answer.getAnsPaId());
        qw.eq("ans_pos",answer.getAnsPos());
        Answer answer2 = answerMapper.selectOne(qw);
        if(answer2 == null){
            //新增
            answer.setAnsId(UUID.randomUUID().toString().replace("-",""));
            answer.setAnsUsrId(user.getUsrId());
            answer.setAnsCreatedate(new Date());
            int line = answerMapper.insert(answer);
            if(line == 1) {return new JsonResult<>(200,"OK");}
            return new JsonResult<>(500,"FAIL");
        }else{
            //修改
            answer2.setAnsSelect(answer.getAnsSelect());
            answer2.setAnsUpdatedate(new Date());
            int line =answerMapper.updateById(answer2);
            if(line ==1) {return new JsonResult<>(200,"OK");}
            return new JsonResult<>(500,"FAIL");
        }

    }
}
