package com.example.exam.service;

import com.example.exam.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {
    User queryByAccount(String usrAccount);

    boolean register(User user);

    List<User> managerUser();

    boolean updateUsrPassword(User user);
}
