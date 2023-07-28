package com.example.exam.service;

import com.example.exam.entity.Paper;
import org.springframework.stereotype.Service;


@Service
public interface PaperService {
    boolean addPaper(Paper paper);
}
