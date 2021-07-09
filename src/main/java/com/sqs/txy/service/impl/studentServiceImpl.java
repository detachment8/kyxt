package com.sqs.txy.service.impl;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.mapper.StudentMapper;
import com.sqs.txy.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-05 21:15
 **/

@Service
public class studentServiceImpl implements studentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public SysStudent findSysStudentByName(String username) {
        return studentMapper.findStudentByUsername(username);
    }
}
