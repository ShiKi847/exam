package com.example.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.exam.dao.PaperMapper;
import com.example.exam.dao.SingleMapper;
import com.example.exam.dao.YesNoMapper;
import com.example.exam.entity.Paper;
import com.example.exam.entity.Single;
import com.example.exam.entity.User;
import com.example.exam.entity.Yesno;
import com.example.exam.service.PaperService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private SingleMapper singleMapper;

    @Autowired
    private YesNoMapper yesNoMapper;
    @Override
    public boolean addPaper(Paper paper) {
        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //出卷人 创建日期
        paper.setPaUsrId(user.getUsrId());
        paper.setPaCreatedate(new Date());
        return paperMapper.insert(paper)==1;
    }

    @Override
    public List<Paper> queryPaperList() {
        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Paper> qw = new QueryWrapper<>();
        qw.eq("pa_usr_id",user.getUsrId());
        qw.isNull("pa_delete");
        return paperMapper.selectList(qw);
    }

    @Override
    public boolean savePaperContent(MultipartFile file ,Integer paId) throws IOException {
        //删除旧的题目内容
        UpdateWrapper<Single> uwSingle = new UpdateWrapper<>();
        uwSingle.eq("sin_pa_id",paId);
        singleMapper.delete(uwSingle);
        UpdateWrapper<Yesno> uwYesno = new UpdateWrapper<>();
        uwYesno.eq("yn_pa_id",paId);
        yesNoMapper.delete(uwYesno);

        //修改试卷的状态
        Paper paper = new Paper();
        paper.setPaId(paId);
        paper.setPaStatus(true);
        paper.setPaUpdatedate(new Date());
        paperMapper.updateById(paper);

        Workbook workbook = null;
        if(file.getOriginalFilename().endsWith(".xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        System.out.println(workbook);
        //获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        //遍历每一行
        for (Row row : sheet) {
            //获取每一列
           String type = row.getCell(0).toString();
           if("单选".equals(type)){
               Integer sinPos =(int)Double.parseDouble(row.getCell(1).toString());
               String sinCaption = row.getCell(2).toString();
               String sinA = row.getCell(3).toString();
               String sinB = row.getCell(4).toString();
               String sinC = row.getCell(5).toString();
               String sinD = row.getCell(6).toString();
               Character sinStandard = row.getCell(7).toString().charAt(0);
               Integer sinPower =(int)Double.parseDouble(row.getCell(8).toString());
               singleMapper.insert(new Single(UUID.randomUUID().toString().replace("-","")
                       ,sinPos,sinCaption,sinA,sinB,sinC,sinD,sinStandard,sinPower,paId,new Date(),null));
           }else if("判断".equals(type)){
                Integer ynPos =(int)Double.parseDouble(row.getCell(1).toString());
                String ynCaption = row.getCell(2).toString();
                Character ynStandard = row.getCell(7).toString().charAt(0);
                Integer ynPower =(int)Double.parseDouble(row.getCell(8).toString());
                yesNoMapper.insert(new Yesno(UUID.randomUUID().toString().replace("-","")
                       ,ynPos,ynCaption,ynStandard,ynPower,paId,new Date(),null));
           }
            //换行
            System.out.println();
        }
        return true;
    }
}
