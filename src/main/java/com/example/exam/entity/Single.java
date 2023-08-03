package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 单选题
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_single") // 表名是谁
public class Single implements Serializable {
    @TableId(value="sin_id") // 支持主键查询
    private String sinId; // ID
    private Integer sinPos; // 序号
    private String sinCaption; // 题目
    private String sinA; // a
    private String sinB; // b
    private String sinC; // c
    private String sinD; // d
    private Character sinStandard; // 标准答案
    private Integer sinPower; // 分值
    private Integer sinPaId; // 关联试卷id
    private Date sinCreatedate; // 创建日期
    private Date sinUpdatedate; // 更新日期
}
