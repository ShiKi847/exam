package com.example.exam.service;

import com.example.exam.entity.Paper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public interface PaperService {
    //添加试卷
    boolean addPaper(Paper paper);

    //查询试卷列表
    List<Paper> queryPaperList();

    //保存题库内容
    boolean savePaperContent(MultipartFile file) throws IOException;
}
