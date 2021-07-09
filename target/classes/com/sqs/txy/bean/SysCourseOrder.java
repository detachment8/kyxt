package com.sqs.txy.bean;

import lombok.Data;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-15 12:23
 **/

@Data
public class SysCourseOrder {
    private String orderNumber;
    private Integer courseId;
    private Integer itemsAmount;

    private SysCourse sysCourses;
}
