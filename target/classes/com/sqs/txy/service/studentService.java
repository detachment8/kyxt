package com.sqs.txy.service;

import com.sqs.txy.bean.SysStudent;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-05 21:15
 **/

public interface studentService {
    //查找学生信息
    public SysStudent findSysStudentByName(String username);
}
