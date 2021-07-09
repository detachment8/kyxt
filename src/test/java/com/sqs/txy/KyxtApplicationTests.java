package com.sqs.txy;

import com.sqs.txy.bean.*;
import com.sqs.txy.bean.utilBean.makeOrderEntity;
import com.sqs.txy.bean.utilBean.orderDetail;
import com.sqs.txy.mapper.*;
import com.sqs.txy.myUtils.UUIDForOrder;
import com.sqs.txy.service.courseService;
import com.sqs.txy.service.orderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class KyxtApplicationTests {

    @Autowired
    private relationMapper relationMapper;

    @Autowired
    private videoMapper videoMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void test1(){
        SysTeacher sysTeacher =new SysTeacher();
        sysTeacher.setTeacherName("sqs");
        sysTeacher.setTeacherSchool("sqs");
        sysTeacher.setTeacherSex(1);
        sysTeacher.setTeacherMajor("sqs");
        teacherMapper.addTeacherInfo(sysTeacher);
    }

}

