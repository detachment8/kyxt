package com.sqs.txy.service.impl;

import com.sqs.txy.bean.*;
import com.sqs.txy.mapper.StudentMapper;
import com.sqs.txy.mapper.TeacherMapper;
import com.sqs.txy.mapper.UserMapper;
import com.sqs.txy.mapper.relationMapper;
import com.sqs.txy.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-02 12:11
 **/

@Service
public class userSeviceImpl implements userService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private relationMapper relationMapper;

    @Override
    public int userCheck(String username, String userpasswor) {
        return userMapper.isUserExist(username,userpasswor);
    }

    @Override
    public int  findUserRole(String username) {
        return userMapper.findRole(username);
    }

    @Override
    //用户类型与信息
    public Object findUserInfo(String username){
        if (findUserRole(username) == 1){
            return userMapper.findSysUser(username);
        }
        else if (findUserRole(username) == 2) {
            return studentMapper.findStudentByUsername(username);
        }
        else if ( findUserRole(username) == 3){
            return teacherMapper.findTeacherByUsername(username);
        }
        else
            return null;
    }

    @Override
    public SysUser findSysuserInfo(String username) {
        return userMapper.findSysUser(username);
    }

    @Override
    //-1表示已存在用户，0表示异常，1表示成功
    public int saveSysuser(SysUser sysUser) {
        if (userMapper.isDepulict(sysUser.getUserName()) ==1 ){
            return -1;
        }
        int flag = userMapper.saveUser(sysUser);
        return flag;

    }

    @Override
    public int isDepulict(String username) {
        return userMapper.isDepulict(username);
    }


    @Override
    public int updateUserInfo(SysUser sysUser, SysStudent sysStudent, SysTeacher sysTeacher) {
        int flag = 1;
        if (sysStudent.getUserName()==null && sysTeacher.getUserName()==null){
            flag =flag & userMapper.updateUser(sysUser);
            return flag;
        }
        if (sysTeacher.getUserName()==null){
            flag =flag & userMapper.updateUser(sysUser);
            flag =flag & studentMapper.updateUser(sysStudent);
            return flag;
        }
        else{
            flag =flag & userMapper.updateUser(sysUser);
            flag =flag & teacherMapper.updateUser(sysTeacher);
            return flag;
        }
    }

    @Override
    public List<SysCourse> findAllUserCourse(String username) {
        return relationMapper.findAllUserCourse(username);
    }
}
