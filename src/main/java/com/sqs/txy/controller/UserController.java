package com.sqs.txy.controller;

import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.bean.SysUser;
import com.sqs.txy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-03-26 16:22
 **/

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private userService userService;

    @Autowired
    private studentService studentService;

    @Autowired
    private teacherService teacherService;

    @Autowired
    private videoService videoService;

    private static String PictureUpLoadPath="E:\\picture";

    @RequestMapping({"/toLogin","login"})
    public String login(){
        System.out.println("toLogin");
        return "login";
    }

    @CrossOrigin
    @RequestMapping("/logincheck")
    public String logincheck(String username, String userpassword, Model model, HttpServletResponse response,HttpServletRequest request){
        System.out.println("logincheck");
       int flag = userService.userCheck(username,userpassword);
        if (flag == 1){
            SysUser sysUser = userService.findSysuserInfo(username);
            model.addAttribute("user",sysUser);
            model.addAttribute("hasUser",true);
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            Cookie cookie =new Cookie("username",sysUser.getUserName());
            response.addCookie(cookie);
           return "index";
       }
       else{
           model.addAttribute("msg","您的登录信息有误！");
           return "login";
       }
    }

    @RequestMapping("/register")
    public String register(){
        System.out.println("register");
        return "register";
    }

    @RequestMapping("/registerCheck")
    public String registerCheck(String username,String userpassword,
                                String email, @RequestParam("phone_number") String phone,
                                HttpServletRequest request,HttpServletResponse response,
                                Model model){
        SysUser sysUser = new SysUser();
        sysUser.setCreateTime(new Date());sysUser.setUpdateTime(new Date());
        sysUser.setUserName(username);sysUser.setSalt("123");
        sysUser.setUserPhone(phone); sysUser.setUserPassword(userpassword);
        sysUser.setUserEmail(email);
        int i = userService.saveSysuser(sysUser);
        if ( i == 1){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            return "index";
        }
        else
        {
            model.addAttribute("msg","你注册的信息有误！");
            return "register";
        }
    }
    //前端remote调用ajax请求来确认是否重复
    @ResponseBody
    @RequestMapping("/checkUserExist")
    public boolean checkUserExist(String username){
        int i = userService.isDepulict(username);
        if (i == 1){
            return false;
        }else
        {
            return true;
        }

    }

    @RequestMapping("/forget")
    public String forget(){
        System.out.println("forget");
        return "findback";
    }

    @RequestMapping("/Info")
    public String Info(Model model,HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        SysStudent student = studentService.findSysStudentByName(username);
        SysUser user = userService.findSysuserInfo(username);
        SysTeacher teacher = teacherService.findSysTeacherByName(username);
        model.addAttribute("studentInfo",student);
        model.addAttribute("teacherInfo",teacher);
        model.addAttribute("userInfo",user);
        return "/user/userInfo";
    }
    //查看课程
    @RequestMapping("/Course")
    public String Course(Model model,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        List<SysCourse> userCourses = userService.findAllUserCourse(username);
        model.addAttribute("userCourses",userCourses);
        SysStudent student = studentService.findSysStudentByName(username);
        SysUser user = userService.findSysuserInfo(username);
        SysTeacher teacher = teacherService.findSysTeacherByName(username);
        model.addAttribute("studentInfo",student);
        model.addAttribute("teacherInfo",teacher);
        model.addAttribute("userInfo",user);
        return "/user/userCourse";
    }
    //修改资料
    @RequestMapping("/updateInfo")
    public String updateInfo(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        SysStudent student = studentService.findSysStudentByName(username);
        SysUser user = userService.findSysuserInfo(username);
        SysTeacher teacher = teacherService.findSysTeacherByName(username);
        model.addAttribute("studentInfo",student);
        model.addAttribute("teacherInfo",teacher);
        model.addAttribute("userInfo",user);
        return "/user/updateInfo";
    }
    //确认修改结果
  @RequestMapping("/comfirmUpdate")
    public String comfirmUpdate(HttpServletRequest request,HttpServletResponse response,
            @RequestParam("Picture") MultipartFile upload,SysUser sysUser,
             SysTeacher teacher,SysStudent student) throws ServletException, IOException {
      String username = (String) request.getSession().getAttribute("username");
      sysUser.setUserName(username);
      if (!upload.getOriginalFilename().equals("")) {
          //获取文件名字加上时间戳
          String fileName = upload.getOriginalFilename();
          String uuid = UUID.randomUUID().toString().replace("-", "");
          fileName = uuid + "_" + fileName;
          File file = new File(PictureUpLoadPath);
          if (!file.exists()) {//不存在就创建文件夹
              file.mkdir();
          }
          upload.transferTo(new File(PictureUpLoadPath, fileName));
          sysUser.setUserPicture(fileName);
      }
      else {
          sysUser.setUserPicture(userService.findSysuserInfo(username).getUserPicture());
      }
      int userRole = userService.findUserRole(sysUser.getUserName());
      if (userRole == 2) {
          student.setUserName(username);
      } else if (userRole == 3) {
          teacher.setUserName(username);
      }

      int flag = userService.updateUserInfo(sysUser, student, teacher);
      if (flag == 1) {
          request.getRequestDispatcher("/user/updateInfo").forward(request, response);
      }
      return "index";
  }

  @RequestMapping("/toUploadCourse")
    public String toUploadCourse(Model model){
      List<String> allCourseClass = videoService.findAllCourseClass();
      model.addAttribute("allCourseClass",allCourseClass);
      return "/video/UploadCourse";
  }
}
