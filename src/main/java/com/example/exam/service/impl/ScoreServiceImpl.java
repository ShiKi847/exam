package com.example.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.exam.dao.*;
import com.example.exam.entity.*;
import com.example.exam.pojo.JsonResult;
import com.example.exam.service.ScoreService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private SingleMapper singleMapper;
    @Autowired
    private YesNoMapper yesNoMapper;

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public JsonResult<?> submit(Integer paId) {
        //查询原先是否登记过成绩
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Score> qwScore = new QueryWrapper<Score>();
        qwScore.eq("sc_pa_id",paId);
        qwScore.eq("sc_usr_id",user.getUsrId());
        Score score = scoreMapper.selectOne(qwScore);
        //查询提交过的所有选择项
        QueryWrapper<Answer> qwAnswer = new QueryWrapper<Answer>();
        qwAnswer.eq("ans_usr_id",user.getUsrId());
        qwAnswer.eq("ans_pa_id",paId);
        List<Answer> answers = answerMapper.selectList(qwAnswer);

        int mark = 0;
        for(Answer answer:answers){
            //查标准答案得分
            //先查单选题
            QueryWrapper<Single> qwSingle = new QueryWrapper<>();
            qwSingle.eq("sin_pa_id",paId);
            qwSingle.eq("sin_pos",answer.getAnsPos());
            Single single = singleMapper.selectOne(qwSingle);
            if(single != null){
                if(single.getSinStandard().equals(answer.getAnsSelect())){
                    mark +=single.getSinPower();
                }
                continue;
            }
            //再查判断题
            QueryWrapper<Yesno> qwYesno = new QueryWrapper<>();
            qwYesno.eq("yn_pa_id",paId);
            qwYesno.eq("yn_pos",answer.getAnsPos());
            Yesno yesno = yesNoMapper.selectOne(qwYesno);
            if(yesno != null){
                if(yesno.getYnStandard().equals(answer.getAnsSelect())){
                    mark += yesno.getYnPower();
                }
            }
        }

        if(score !=null){
            //更新
            score.setScMark(mark);
            score.setScCreatedate(new Date());
            scoreMapper.updateById(score);
        }else{
            //新增
            score = new Score(UUID.randomUUID().toString().replace("-",""),
                    paId, user.getUsrId(),mark,new Date(),null);
            scoreMapper.insert(score);
        }
        return new JsonResult<>(200,"OK");
    }

    @Override
    public List<Score> selectList() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Score> qw = new QueryWrapper<Score>();
        qw.eq("sc_usr_id",user.getUsrId());
        List<Score> scoreList = scoreMapper.selectList(qw);
        //遍历成绩列表，根据试卷ID查询试卷名字
        for (Score score : scoreList) {
            Integer scPaId = score.getScPaId();
            Paper paper = paperMapper.selectById(scPaId);
            score.setScId(paper.getPaName());
        }
        return scoreList;

    }
}
