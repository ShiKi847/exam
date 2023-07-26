package com.example.exam.realm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exam.dao.UserMapper;
import com.example.exam.entity.User;
import com.example.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByAccount(String usrAccount) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("usr_account",usrAccount);
        return userMapper.selectOne(qw);
    }
}
