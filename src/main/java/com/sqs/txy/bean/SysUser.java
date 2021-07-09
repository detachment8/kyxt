package com.sqs.txy.bean;

import lombok.Data;

import java.util.Date;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-02 11:58
 **/

@Data
public class SysUser {

    private String userName;
    private String userPassword;
    private String salt;
    private Integer userStatus;
    private Date createTime;
    private  Date updateTime;
    private String userPhone;
    private String userEmail;
    private String userPicture;
}
