package com.sqs.txy.mapper;


import com.sqs.txy.bean.SysUser;
import com.sqs.txy.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //判断用户名密码是否正确
    @Select("select count(*) from sys_user where user_name=#{username} and user_password=#{userpassword}")
    public  int isUserExist(String username,String userpassword);


    //判断用户是否存在
    @Select("select count(*) from sys_user where user_name=#{username}")
    public  int isDepulict(String username);

    //1管理员，2学生，3教师
    @Select("select role_id from sys_user_role where user_name=#{username}")
    public  int findRole(String username);


    //1管理员，2学生，3教师
    @Select("select * from sys_user where user_name=#{username}")
    public SysUser findSysUser(String username);

    //保存用户
    @Insert("INSERT INTO `myproject`.`sys_user`" +
            "(`user_name`, `user_password`, `salt`, `create_time`, `update_time`, `user_phone`,`user_email`) " +
            "VALUES (#{userName}, #{userPassword}, #{salt}, #{createTime}, #{updateTime}, #{userPhone}, #{userEmail})")
    public int saveUser(SysUser sysUser);

    //更新用户信息
    @Update("UPDATE `myproject`.`sys_user` " +
            "SET  `user_phone` = #{userPhone}, `user_email` = #{userEmail}, " +
            "`user_picture` = #{userPicture} WHERE `user_name` = #{userName}")
    public int updateUser(SysUser sysUser);
}
