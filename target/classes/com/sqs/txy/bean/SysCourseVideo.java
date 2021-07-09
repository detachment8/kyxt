package com.sqs.txy.bean;

import lombok.Data;

import java.util.Date;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-17 14:43
 **/

@Data
public class SysCourseVideo {
    private Integer videoId;
    private Integer courseId;
    private String videoName;
    private String videoLocation;
    private Date videoCreateTime;
    private Float videoLong;
    private Integer videoStatus;
}
