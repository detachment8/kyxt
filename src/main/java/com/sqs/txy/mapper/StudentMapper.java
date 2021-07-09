package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentMapper {

    @Select("select * from sys_student where user_name=#{username} and status = 1")
    public SysStudent findStudentByUsername(String username);
    //更新学生信息
    @Update("UPDATE `myproject`.`sys_student` " +
            "SET `student_name` = #{studentName}, `student_school` = #{studentSchool}, " +
            "`student_major` = #{studentMajor} WHERE `user_name` = #{userName}")
    public int updateUser(SysStudent sysStudent);

    //加入学生信息
    @Insert("INSERT INTO `myproject`.`sys_student`" +
            "(`user_name`, `student_major`, `student_name`, `student_school`, `student_sex`,`status`) " +
            "VALUES (#{userName}, #{studentMajor}, #{studentName}, #{studentSchool}, #{studentSex}, 0)")
    public  int addStudentInfo(SysStudent sysStudent);
}
