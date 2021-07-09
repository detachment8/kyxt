package com.sqs.txy.controller;

import com.sqs.txy.bean.SysStudent;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.service.autService;
import com.sqs.txy.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.dc.pr.PRError;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-21 10:27
 **/

@Controller
@RequestMapping("Authentication")
public class autController {

    @Autowired
    private autService autServie;

    private static String PictureUpLoadPath="E:\\picture";

    @RequestMapping("toAut")
    public String toAut(){
        return "/Aut/AutIndex";
    }



    @RequestMapping("AutTeacher")
    public String AutTeacher(SysTeacher teacher, @RequestParam("picture") MultipartFile upload
            , HttpServletRequest request, Model model) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        String fileName=null;
        if (!upload.getOriginalFilename().equals("")) {
            //获取文件名字加上时间戳
            fileName = upload.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + "_" + fileName;
            File file = new File(PictureUpLoadPath);
            if (!file.exists()) {//不存在就创建文件夹
                file.mkdir();
            }
            upload.transferTo(new File(PictureUpLoadPath, fileName));
        }

        teacher.setUserName(username);
        int i = autServie.saveTeacherAutInfo(teacher, fileName);
        if (i == 1 ){
                return "/Aut/autSuccess";
        }else{
            model.addAttribute("msg","您的认证信息有误，请重新认证！");
            return "/Aut/AutIndex";
        }
    }

    @RequestMapping("AutStudent")
    public String AutStudent(SysStudent student, @RequestParam("picture") MultipartFile upload
            , HttpServletRequest request, Model model) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        String fileName=null;
        if (!upload.getOriginalFilename().equals("")) {
            //获取文件名字加上时间戳
            fileName = upload.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + "_" + fileName;
            File file = new File(PictureUpLoadPath);
            if (!file.exists()) {//不存在就创建文件夹
                file.mkdir();
            }
            upload.transferTo(new File(PictureUpLoadPath, fileName));
        }

        student.setUserName(username);
        int i = autServie.saveStudentAutInfo(student, fileName);
        if (i == 1 ){
            return "/Aut/autSuccess";
        }else{
            model.addAttribute("msg","您的认证信息有误，请重新认证！");
            return "/Aut/AutIndex";
        }
    }
}
