package com.example.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_user") // 表名是谁
public class User {
    @TableId(value = "usr_id",type = IdType.AUTO) // 支持主键查询
    private Integer usrId;
    private String usrName;
    private String usrAccount;
    private String usrPassword;
    private String usrSalt;
    private String usrRole;
    @TableField (strategy=FieldStrategy.IGNORED)
    private Boolean usrDelete;
    private Date usrCreatedate;
    private Date usrUpdatedate;
}
