package com.sqs.txy.bean;

import lombok.Data;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-02 14:41
 **/

@Data
public class SysTeacher {
    private Integer id;
    private  String userName;
    private String teacherName;
    private String teacherSchool;
    private String teacherMajor;
    private Integer teacherSex;
    private String teacherInstitution;
}
