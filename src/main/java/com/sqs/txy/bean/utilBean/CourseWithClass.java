package com.sqs.txy.bean.utilBean;

import com.sqs.txy.bean.SysCourse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-09 12:32
 **/

@Data
public class CourseWithClass {
    private SysCourse sysCourse;
    private List<String> courseClass;
}
