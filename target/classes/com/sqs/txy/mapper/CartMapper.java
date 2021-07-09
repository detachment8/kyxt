package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysCartInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CartMapper {

    //判断有无商品在购物车中已经存在
    @Select("select count(*) from sys_cart where user_name=#{userName} and course_id=#{courseId}")
    public int isExistInCart(String userName,Integer courseId);

    //在购物车中添加新的商品
    @Insert("INSERT INTO `myproject`.`sys_cart`(`user_name`, `course_id`, `course_amount`) VALUES (#{userName}, #{courseId}, 1) ")
    public int addCourseToCart(String userName,Integer courseId);

    //查出购物车中的所有商品和信息
    @Select("select r.course_id  course_id,r.course_amount course_amount,c.course_name,s.id,c.course_supporter,c.course_price,c.course_picture\n" +
            "FROM sys_cart r,sys_course c,sys_teacher s\n" +
            "WHERE r.course_id=c.course_id AND r.user_name=#{userName} AND s.user_name = c.course_supporter")
    @Results({
            @Result(column = "course_id", property = "courseId"),
            @Result(column = "course_amount", property = "courseAmount"),
            @Result(column = "course_name",property = "courseName"),
            @Result(column = "id",property = "supporterId"),
            @Result(column = "course_supporter",property = "courseSupporter"),
            @Result(column = "course_price",property = "coursePrice"),
            @Result(column = "course_picture",property = "coursePicture")
    })
    public List<SysCartInfo> findCartInfo(String userName);

    //移除购物车的一个商品
    @Delete("DELETE FROM `myproject`.`sys_cart` WHERE `user_name` = #{userName} and course_id=#{courseId} ")
    public  int removeCourse(String userName,Integer courseId);
    //移除购物车的全部商品
    @Delete("DELETE FROM `myproject`.`sys_cart` WHERE `user_name` = #{userName}")
    public  int removeAllCourse(String userName);


}
