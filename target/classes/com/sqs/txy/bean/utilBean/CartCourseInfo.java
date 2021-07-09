package com.sqs.txy.bean.utilBean;

import com.sqs.txy.bean.SysCartInfo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-10 09:42
 **/

@Data
public class CartCourseInfo {
    private Integer courseId;
    private Integer courseAmount;
    private String courseName;
    private BigDecimal coursePrice;
    private  String coursePicture;

    public CartCourseInfo(){

    };

    public CartCourseInfo(SysCartInfo sysCartInfo){
        this.courseId = sysCartInfo.getCourseId();
        this.courseAmount = sysCartInfo.getCourseAmount();
        this.courseName = sysCartInfo.getCourseName();
        this.coursePrice = sysCartInfo.getCoursePrice();
        this.coursePicture = sysCartInfo.getCoursePicture();
    }
}
