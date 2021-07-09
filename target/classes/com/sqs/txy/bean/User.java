package com.sqs.txy.bean;

import lombok.Data;

import java.util.Date;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-02 15:06
 * 包装类，代表通用用户
 **/
@Data
public class User {

    private String userName;
    private String userPassword;
    /*
    加盐
    * */
    private String salt;
    /*
     * 0表示禁用，1表示启用
     * */
    private Integer userStatus;
    private Date createTime;
    private  Date updateTime;
    private String userPicture;

}
