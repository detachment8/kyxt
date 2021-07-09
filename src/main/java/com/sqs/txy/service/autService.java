package com.sqs.txy.service;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;

public interface autService {

    //教师认证信息
    public int saveTeacherAutInfo( SysTeacher teacher,String pictureLocation);
    //学生认证
    public int saveStudentAutInfo( SysStudent student, String pictureLocation);

}
