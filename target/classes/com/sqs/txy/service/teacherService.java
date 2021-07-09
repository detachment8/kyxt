package com.sqs.txy.service;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;

public interface teacherService {
    //查找老师信息
    public SysTeacher findSysTeacherByName(String username);
}
