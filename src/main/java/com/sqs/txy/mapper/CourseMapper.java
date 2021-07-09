package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.bean.SysTeacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select * from sys_course")
    public List<SysCourse> findAllCourse();

    @Select("select * from sys_course where course_name like '%${SearcherItem}%'" +
            "or course_supporter like '%${SearcherItem}%' or course_major like '%${SearcherItem}%' or" +
            " course_description like '%${SearcherItem}%' and course_status = 1")
    public List<SysCourse> findWithSearchItem(String SearcherItem);

    @Select("select * from sys_course where course_id=#{courseId}")
    public SysCourse findCourseById(Integer courseId);

    @Select("select s.* from sys_teacher s,sys_course r" +
            " where r.course_id=#{courseId} and s.user_name = r.course_supporter")
    public SysTeacher findTeacherByCourseId(Integer courseId);

    @Insert("INSERT INTO `myproject`.`sys_course`" +
            "(`course_name`, `course_supporter`, `course_price`, `course_major`, `course_description`," +
            " `course_create_time`, `course_update_time`, `course_picture`, `course_long`,`course_status`)" +
            " VALUES (#{courseName}, #{courseSupporter}, #{coursePrice}, #{courseMajor}, " +
            "#{courseDescription},#{courseCreateTime}, #{courseUpdateTime}, #{coursePicture},#{courseLong},0)")
    public int saveCourse(SysCourse sysCourse);

    //查找最近一次插入的课程
    @Select("select course_id from sys_course\n" +
            "WHERE course_supporter=#{username}\n" +
            "order by course_create_time desc\n" +
            "limit 1")
    public Integer findTheLastCourseId(String username);
}
