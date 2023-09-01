package com.shore.distribution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String userName;
    private String userPwd;
    private int userBalance;
    private String fissionPathway;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;

}
