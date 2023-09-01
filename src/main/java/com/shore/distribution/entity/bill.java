package com.shore.distribution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class bill {
    private int id;
    private int userId;
    private String billType;
    private int payMoney;
    private String billNode;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
