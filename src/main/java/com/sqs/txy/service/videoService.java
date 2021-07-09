package com.sqs.txy.service;


import com.sqs.txy.bean.SysCourseVideo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface videoService {

    //查询课程的所有的类别
    public List<String> findAllCourseClass();

    //加入视频
    public int addVideo(SysCourseVideo sysCourseVideo);

    //通过课程id找到所有的视频
    public List<SysCourseVideo> findAllCourseVideo(Integer courseId);

    //通过id查询视频
    public SysCourseVideo findCourseVideoByVideoId(Integer videoId);

}
