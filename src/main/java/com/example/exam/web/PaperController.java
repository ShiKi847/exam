package com.example.exam.web;

import com.example.exam.entity.Paper;
import com.example.exam.pojo.JsonResult;
import com.example.exam.service.PaperService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * 试卷
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;
    //添加试卷
    @GetMapping("/addPaper")
    @RequiresRoles("ADMIN")
    public String addPaper(){
        return "addPaper";
    }
    //添加试卷
    @PostMapping("/addPaper")
    @RequiresRoles("ADMIN")
    public String addPaper(Paper paper, Model model){
        boolean flag=paperService.addPaper(paper);
        if(flag) {return "redirect:/index";}
        model.addAttribute("tip","卷名或密码错误");
        return "addPaper";
    }
    //管理试卷
    @GetMapping("/managerPaper")
    @RequiresRoles("ADMIN")
    public String managerPaper(Model model){
        List<Paper> paperList = paperService.queryPaperList();
        model.addAttribute("paperList",paperList);
        return "managerPaper";
    }
    //上传文件
    @PostMapping("/upload")
    @ResponseBody
    public boolean upload(MultipartFile file, Integer paId,@Value("${file.upload}") String path) throws IOException {
        if(file.isEmpty()) {return false;}
        boolean flag =paperService.savePaperContent(file,paId);
        if(flag) {file.transferTo(new File(path,file.getOriginalFilename()));}
        return flag;
    }

    //在线考试
    @GetMapping("/onlineTest")
    @RequiresRoles("USER")
    public String onlineTest(){
        return "onlineTest";
    }

    //在线考试
    @PostMapping("/onlineTest")
    @RequiresRoles("USER")
    @ResponseBody
    public JsonResult<Paper> onlineTest(Integer paId, String paPassword){
        return paperService.queryPaper(paId,paPassword);
    }

    @PostMapping("/question")
    @RequiresRoles("USER")
    @ResponseBody
    public JsonResult<Serializable> question(Integer paId, Integer pos){
        return paperService.queryQuestion(paId,pos);
    }

    //修改密码
    @PostMapping("/updatePassword")
    @RequiresRoles("ADMIN")
    @ResponseBody
    public boolean updatePassword(Paper paper){
        return paperService.updatePassword(paper);
    }

    //修改试卷状态
    @PostMapping("/updateStatus")
    @RequiresRoles("ADMIN")
    @ResponseBody
    public boolean updateStatus(Integer paId,Boolean paStatus){
        return paperService.updateStatus(paId,paStatus);
    }

}
