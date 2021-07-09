package com.sqs.txy.service.impl;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.mapper.StudentMapper;
import com.sqs.txy.mapper.TeacherMapper;
import com.sqs.txy.mapper.autMapper;
import com.sqs.txy.service.autService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-21 16:04
 **/


@Service
public class autServiceImpl implements autService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private autMapper autMapper;
    @Override
    public int saveTeacherAutInfo(SysTeacher teacher, String pictureLocation) {
        if (pictureLocation == null || teacher == null||teacher.getUserName()==null){
            return 0;
        }
        int flag = 1;
        flag = flag & teacherMapper.addTeacherInfo(teacher);
        flag = flag & autMapper.addAutInfo(teacher.getUserName(),pictureLocation);
        return flag;
    }

    @Override
    public int saveStudentAutInfo(SysStudent student, String pictureLocation) {
        if (pictureLocation == null || student == null||student.getUserName()==null){
            return 0;
        }
        int flag = 1;
        flag = flag & studentMapper.addStudentInfo(student);
        flag = flag & autMapper.addAutInfo(student.getUserName(),pictureLocation);
        return flag;
    }
}
