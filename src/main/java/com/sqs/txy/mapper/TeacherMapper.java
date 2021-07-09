package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.bean.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface TeacherMapper {

    @Select("select * from sys_teacher where user_name=#{username} and status = 1")
    public SysTeacher findTeacherByUsername(String username);

    @Update("UPDATE `myproject`.`sys_teacher` " +
            "SET `teacher_name` = #{teacherName}, `teacher_school` = #{teacherSchool}, " +
            "`teacher_major` = #{teacherMajor} WHERE `user_name` = #{userName}")
    public int updateUser(SysTeacher sysTeacher);

    @Insert("INSERT INTO `myproject`.`sys_teacher`" +
            "(`user_name`, `teacher_name`, `teacher_school`, `teacher_major`, `teacher_sex`,`status`) " +
            "VALUES (#{userName}, #{teacherName}, #{teacherSchool}, #{teacherMajor}, #{teacherSex},0)")
    public int addTeacherInfo(SysTeacher sysTeacher);
}
