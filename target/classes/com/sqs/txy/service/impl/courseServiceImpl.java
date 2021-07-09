package com.sqs.txy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.bean.utilBean.CourseWithClass;
import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.mapper.CourseMapper;
import com.sqs.txy.mapper.CourseclassMapper;
import com.sqs.txy.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-07 17:19
 **/

@Service
public class courseServiceImpl implements courseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseclassMapper courseclassMapper;
    @Override
    public PageInfo<SysCourse> findSearchCourse(String searchItem,Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<SysCourse> all = courseMapper.findWithSearchItem(searchItem);
        PageInfo<SysCourse> info = new PageInfo(all);
        return info;
    }

    @Override
    public  PageInfo<SysCourse> pageTest(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<SysCourse> all = courseMapper.findAllCourse();
        PageInfo<SysCourse> info = new PageInfo(all);
        return info;
    }

    @Override
    public SysCourse findCourseById(Integer courseId) {

        return courseMapper.findCourseById(courseId);
    }

    @Override
    public CourseWithClass findCourseWithClassById(Integer courseId) {
        CourseWithClass courseWithClass = new CourseWithClass();
        SysCourse courseById = courseMapper.findCourseById(courseId);
        List<String> list = courseclassMapper.findCourseclassnameByid(courseId);
        courseWithClass.setCourseClass(list);
        courseWithClass.setSysCourse(courseById);
        return courseWithClass;
    }

    @Override
    public Integer addCourse(SysCourse sysCourse) {
            sysCourse.setCourseCreateTime(new Date());
        int i = courseMapper.saveCourse(sysCourse);
        if (i==1){
            return courseMapper.findTheLastCourseId(sysCourse.getCourseSupporter());
        }
        return null;
    }

    @Override
    public SysTeacher findTeacherByCourseId(Integer courseId) {
        return courseMapper.findTeacherByCourseId(courseId);
    }
}
