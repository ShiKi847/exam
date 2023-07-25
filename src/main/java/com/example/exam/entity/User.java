package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_user") // 表名是谁
public class User {
    @TableId("usr_id") // 支持主键查询
    private Integer usrId;
    private String usrName;
    private String usrAccount;
    private String usrPassword;
    private String usrSalt;
    private String usrRole;
    private Boolean usrDelete;
    private Date usrCreatedate;
    private Date usrUpdatedate;
}
