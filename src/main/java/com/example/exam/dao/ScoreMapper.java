package com.example.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exam.entity.Score;
import com.example.exam.entity.Yesno;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreMapper extends BaseMapper<Score> {
}
