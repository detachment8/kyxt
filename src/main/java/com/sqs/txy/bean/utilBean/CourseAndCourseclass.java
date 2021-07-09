package com.sqs.txy.bean.utilBean;

import com.sqs.txy.bean.SysCourseclass;
import lombok.Data;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-09 12:35
 **/

@Data
public class CourseAndCourseclass {
    private Integer courseclassId;
    private Integer courseId;
    private List<SysCourseclass> sysCourseclasses;
}
