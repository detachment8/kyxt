package com.sqs.txy.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-07 16:11
 **/

@Data
public class SysCourse {
    private  Integer courseId;
    private String courseName;
    private String courseSupporter;
    private BigDecimal coursePrice;
    private String  courseMajor;
    private String courseDescription;
    private Date    courseCreateTime;
    private Date    courseUpdateTime;
    private String coursePicture;
    private BigDecimal courseLong;
    private Integer courseStatus;
    private List<SysCourseclass> courseclasses;
}
