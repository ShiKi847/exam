package com.example.exam.service;

import com.example.exam.entity.Paper;
import com.example.exam.pojo.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


@Service
public interface PaperService {
    //添加试卷
    boolean addPaper(Paper paper);

    //查询试卷列表
    List<Paper> queryPaperList();

    //保存题库内容
    boolean savePaperContent(MultipartFile file ,Integer paId) throws IOException;

    JsonResult<Paper> queryPaper(Integer paId, String paPassword);

    JsonResult<Serializable> queryQuestion(Integer paId, Integer pos);


    boolean updatePassword(Paper paper);


    boolean updateStatus(Integer paId,Boolean paStatus);
}
