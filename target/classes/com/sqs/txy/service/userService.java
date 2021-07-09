package com.sqs.txy.service;

import com.sqs.txy.bean.*;

import java.util.List;

public interface userService {

    //比对用户信息
    public int userCheck(String username,String userpasswor);
    //根据用户名返回用户的角色信息 1管理员2学生3老师
    public int findUserRole(String username);
    //根据用户名返回用户的信息
    public Object findUserInfo(String username);
    //根据用户名返回系统用户的信息
    public SysUser findSysuserInfo(String username);
    //注册新用户
    public int saveSysuser(SysUser sysUser);
    //判断用户是否已存在
    public int isDepulict(String username);
    //接受文件并且上传文件
    public int updateUserInfo(SysUser sysUser, SysStudent sysStudent, SysTeacher sysTeacher);
    //查询一个用户所拥有的全部课程、
    public List<SysCourse> findAllUserCourse(String username);
}
