package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 答题
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_answer") // 表名是谁
public class Answer implements Serializable {
    @TableId(value="ans_id") // 支持主键查询
    private String ansId; // ID
    private Integer ansUsrId; // 关联用户ID
    private Integer ansPaId; // 关联试卷ID
    private Integer ansPos; // 序号
    private Character ansSelect; // 选择答案
    private Date ansCreatedate; // 创建日期
    private Date ansUpdatedate; // 更新日期
}
