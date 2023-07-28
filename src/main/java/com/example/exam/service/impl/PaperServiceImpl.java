package com.example.exam.service.impl;

import com.example.exam.dao.PaperMapper;
import com.example.exam.entity.Paper;
import com.example.exam.entity.User;
import com.example.exam.service.PaperService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Override
    public boolean addPaper(Paper paper) {
        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //出卷人 创建日期
        paper.setPaUsrId(user.getUsrId());
        paper.setPaCreatedate(new Date());
        return paperMapper.insert(paper)==1;
    }
}
