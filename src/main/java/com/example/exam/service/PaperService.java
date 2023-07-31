package com.example.exam.service;

import com.example.exam.entity.Paper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PaperService {
    boolean addPaper(Paper paper);

    List<Paper> queryPaperList();
}
