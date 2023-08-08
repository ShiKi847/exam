package com.example.exam.service;

import com.example.exam.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService  {
    User queryByAccount(String usrAccount);

    boolean register(User user);
}
