package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 成绩
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_score") // 表名是谁
public class Score implements Serializable {
    @TableId(value="sc_id") // 支持主键查询
    private String scId; // ID
    private Integer scPaId; // 关联试卷ID
    private Integer scUsrId; // 关联学生ID
    private Integer scMark; // 分数
    private Date scCreatedate; // 创建日期
    private Date scUpdatedate; // 更新日期
}
