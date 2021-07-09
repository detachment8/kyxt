package com.sqs.txy.service.impl;

import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.mapper.TeacherMapper;
import com.sqs.txy.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-05 22:01
 **/

@Service
public class teacherServiceImpl implements teacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public SysTeacher findSysTeacherByName(String username) {
        return teacherMapper.findTeacherByUsername(username);
    }
}
