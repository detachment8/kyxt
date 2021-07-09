package com.sqs.txy.mapper;


import com.sqs.txy.bean.SysCourseVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface videoMapper {

    //加入课程视频
    @Insert("INSERT INTO `myproject`.`sys_course_video`" +
            "(`course_id`, `video_name`, `video_location`, `video_create_time`, `video_long`, `video_status`) " +
            "VALUES (#{courseId}, #{videoName}, #{videoLocation}, #{videoCreateTime}, #{videoLong}, 0)")
    public int addVideo(SysCourseVideo sysCourseVideo);

    //查询一个课程的所有视频
    @Select("select * from sys_course_video" +
            " where course_id=#{courseId} and video_status = 1")
    public List<SysCourseVideo> findAllCourseVideo(Integer courseId);

    //通过id查询视频
    @Select("select * from sys_course_video" +
            " where video_id=#{videoId} and video_status = 1")
    public SysCourseVideo findCourseVideoByVideoId(Integer videoId);
}
