package com.example.exam.service;

import com.example.exam.entity.User;

public interface UserService  {
    User queryByAccount(String usrAccount);

    boolean register(User user);
}
