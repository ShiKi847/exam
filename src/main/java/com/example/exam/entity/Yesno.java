package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 判断题
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_yesno") // 表名是谁
public class Yesno implements Serializable {
    @TableId(value="yn_id") // 支持主键查询
    private String ynId; // ID
    private Integer ynPos; // 序号
    private String ynCaption; // 题目
    private Character ynStandard; // 标准答案
    private Integer ynPower; // 分值
    private Integer ynPaId; // 关联试卷id
    private Date ynCreatedate; // 创建日期
    private Date ynUpdatedate; // 更新日期
}
