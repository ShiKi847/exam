package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试卷
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_paper") // 表名是谁
public class Paper {
    @TableId(value="pa_id", type= IdType.AUTO) // 支持主键查询和ID自增
    private Integer paId; // ID
    private String paName; // 名称
    private String paPassword; // 密码
    private Boolean paStatus; // 状态：null未上传，0上架，1下架
    private Integer paUsrId; // 出卷老师ID
    private String paFilename; // 上传到服务器的试卷文件
    private Boolean paDelete; // 删除
    private Date paCreatedate; // 创建日期
    private Date paUpdatedate; // 更新日期
}
