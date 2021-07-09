package com.sqs.txy.service;

import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.bean.utilBean.CourseWithClass;
import com.sqs.txy.bean.SysCourse;

public interface courseService {

    //搜索课程
    public PageInfo<SysCourse> findSearchCourse(String searchItem, Integer page, Integer pageSize);

    //分页测试
    public PageInfo<SysCourse> pageTest(int page, int pageSize);

    //通过id查询课程
    public SysCourse findCourseById(Integer courseId);

    //通过id查询课程以及类别
    public CourseWithClass findCourseWithClassById(Integer courseId);

    //加入课程,返回课程id
    public Integer addCourse(SysCourse sysCourse);

    //通过课程id，找到课程的提供者
    public SysTeacher findTeacherByCourseId(Integer courseId);
}
