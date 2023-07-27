package com.example.exam.realm.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exam.dao.UserMapper;
import com.example.exam.entity.User;
import com.example.exam.service.UserService;
import com.example.exam.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Override
    public boolean register(User user) {
        String salt = EncryptUtil.generateSalt();
        String password = EncryptUtil.generatePassword(user.getUsrPassword(), salt);
        user.setUsrPassword(password);
        user.setUsrSalt(salt);
        user.setUsrCreatedate(new Date());
        return userMapper.insert(user) == 1;
    }
}
