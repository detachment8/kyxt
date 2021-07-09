package com.sqs.txy.service.impl;

import com.sqs.txy.bean.SysCourseVideo;
import com.sqs.txy.mapper.CourseclassMapper;
import com.sqs.txy.mapper.videoMapper;
import com.sqs.txy.service.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-17 15:03
 **/

@Service
public class videoServiceImpl implements videoService {
    @Autowired
    private videoMapper videoMapper;
    @Autowired
    private CourseclassMapper CourseclassMapper;

    @Override
    public List<String> findAllCourseClass() {
        return CourseclassMapper.findAllCourseClass();
    }

    @Override
    public int addVideo(SysCourseVideo sysCourseVideo) {
        sysCourseVideo.setVideoCreateTime(new Date());
        return videoMapper.addVideo(sysCourseVideo);
    }

    @Override
    public List<SysCourseVideo> findAllCourseVideo(Integer courseId) {
        return videoMapper.findAllCourseVideo(courseId);
    }

    @Override
    public SysCourseVideo findCourseVideoByVideoId(Integer videoId) {
        return videoMapper.findCourseVideoByVideoId(videoId);
    }
}
