package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface relationMapper {

    //在用户所拥有的课程里加入课程
    @Insert("insert into sys_user_course values(#{orderNumber},#{courseId})")
    public int addCourseForUser(String orderNumber,Integer courseId);

    //查询一个用户所拥有的全部课程
    @Select("select * " +
            "from sys_course s,sys_user_course r " +
            "where r.user_name=#{username} and s.course_id = r.course_id")
    public List<SysCourse> findAllUserCourse(String username);
}
