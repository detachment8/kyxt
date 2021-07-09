package com.sqs.txy.mapper;

import com.sqs.txy.bean.utilBean.CourseAndCourseclass;
import com.sqs.txy.bean.SysCourseclass;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CourseclassMapper {
    //通过类别id查询类别信息
    @Select("SELECT * FROM `myproject`.`sys_courseclass` where courseclass_id = #{courseclassId}")
    public SysCourseclass findClassById(Integer courseclassId);
    //通过课程id查询所有类别的ID
    @Select("select * from sys_class_and_course where course_id = #{courseId}")
    public CourseAndCourseclass findClassAndCourseById(Integer courseuId);
    //通过课程id查询所有的类别名
    @Select("select s.courseclass_name from sys_class_and_course r,sys_courseclass s " +
            "where r.courseclass_id = s.courseclass_id and r.course_id = #{courseId}")
    public List<String> findCourseclassnameByid(Integer courseuId);

    //查询课程的所有的类别
    @Select("select courseclass_name from sys_courseclass")
    public List<String> findAllCourseClass();

    @Select("select * from  sys_class_and_course where course_id = #{courseId}")
    @Results( value = {
            @Result(column = "course_id",property = "courseId"),
            @Result(column = "courseclass_id",property = "courseId"),
            @Result(column = "courseclass_id",property = "sysCourseclasses",
            many = @Many(
                    select = "com.sqs.txy.mapper.CourseclassMapper.findClassById",
                    fetchType= FetchType.LAZY
            ))
    })
    public List<CourseAndCourseclass> findFullClassById(Integer courseuId);
}
