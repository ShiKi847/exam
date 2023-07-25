package com.example.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exam.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
