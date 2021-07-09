package com.sqs.txy.controller;

import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.bean.SysCourseVideo;
import com.sqs.txy.bean.SysTeacher;
import com.sqs.txy.bean.SysUser;
import com.sqs.txy.service.courseService;
import com.sqs.txy.service.userService;
import com.sqs.txy.service.videoService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-17 09:28
 **/


@RequestMapping("/video")
@Controller
public class videoController {

    private static String PictureUpLoadPath="E:\\picture";
    private static String VideoUpLoadPath="E:\\video";
    @Autowired
    private courseService courseService;
    @Autowired
    private videoService videoService;
    @Autowired
    private userService userService;

    //视频播放页
    @RequestMapping("/playVideo/{courseId}")
    public String playVideo(@PathVariable(value ="courseId") Integer courseId,
                            Model model){
        System.out.println(courseId);
        //老师的信息
        SysTeacher teacher = courseService.findTeacherByCourseId(courseId);
        //用户信息
        SysUser user = userService.findSysuserInfo(teacher.getUserName());
        //课程信息
        SysCourse course = courseService.findCourseById(courseId);
        //课程所包含的全部信息
        List<SysCourseVideo> videos = videoService.findAllCourseVideo(courseId);
        model.addAttribute("videos",videos);
        model.addAttribute("teacher",teacher);
        model.addAttribute("course",course);
        model.addAttribute("teacherPicture",user.getUserPicture());
        model.addAttribute("playVideo",videos.get(0));
        return "/video/playVideo";
    }


    //视频播放页
    @RequestMapping("/switchVideo/{videoId}")
    public String switchVideo(@PathVariable(value ="videoId") Integer videoId,
                            Model model){
        System.out.println(videoId);
        SysCourseVideo playVideo = videoService.findCourseVideoByVideoId(videoId);
        Integer courseId = playVideo.getCourseId();
        //老师的信息
        SysTeacher teacher = courseService.findTeacherByCourseId(courseId);
        //用户信息
        SysUser user = userService.findSysuserInfo(teacher.getUserName());
        //课程信息
        SysCourse course = courseService.findCourseById(courseId);
        //课程所包含的全部信息
        List<SysCourseVideo> videos = videoService.findAllCourseVideo(courseId);
        model.addAttribute("videos",videos);
        model.addAttribute("teacher",teacher);
        model.addAttribute("course",course);
        model.addAttribute("teacherPicture",user.getUserPicture());
        model.addAttribute("playVideo",playVideo);
        return "/video/playVideo";
    }

    //上传视频第一步
    @RequestMapping("/uploadStepOne")
    public String uploadStepOne(@RequestParam("Picture") MultipartFile upload, SysCourse sysCourse
                                , HttpServletRequest request, Model model) throws IOException {
        System.out.println(sysCourse);
        String username = (String) request.getSession().getAttribute("username");
        sysCourse.setCourseSupporter(username);
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
            sysCourse.setCoursePicture(fileName);
        }
        System.out.println(sysCourse);
        Integer courseId = courseService.addCourse(sysCourse);
        if (courseId!=null){
            model.addAttribute("courseId",courseId);
            return "/video/uploadStepTwo";
        }
        else {
            return "/video/UploadCourse";
        }
    }


    //上传视频第二步
    @RequestMapping("/uploadStepTwo")
    public String uploadStepTwo(@RequestParam("video") MultipartFile upload, SysCourseVideo sysCourseVideo
            , HttpServletRequest request, Model model,@RequestParam("courseId") Integer courseId) throws IOException {
        System.out.println(sysCourseVideo);
        sysCourseVideo.setCourseId(courseId);
        if (!upload.getOriginalFilename().equals("")) {
            //获取文件名字加上时间戳
            String fileName = upload.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + "_" + fileName;
            File file = new File(VideoUpLoadPath);
            if (!file.exists()) {//不存在就创建文件夹
                file.mkdir();
            }
            upload.transferTo(new File(VideoUpLoadPath, fileName));
            sysCourseVideo.setVideoLocation(fileName);
        }
        System.out.println(sysCourseVideo);
        videoService.addVideo(sysCourseVideo);
        if (courseId!=null){
            model.addAttribute("courseId",courseId);
            return "/video/uploadStepThree";
        }
        else {
            return "/video/UploadCourse";
        }
    }
}
