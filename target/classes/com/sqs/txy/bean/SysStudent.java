package com.sqs.txy.bean;

import lombok.Data;

import java.net.Inet4Address;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-02 14:41
 **/

@Data
public class SysStudent {
    private Integer id;
    private  String userName;
    private String studentName;
    private String studentSchool;
    private String studentMajor;
    private Integer studentSex;
}
