package com.sqs.txy.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-09 22:52
 *
 * 购物车信息的工具类
 **/
@Data
public class SysCartInfo {
    private  Integer courseId;
    private Integer courseAmount;
    private String courseName;
    private Integer supporterId;
    private String courseSupporter;
    private BigDecimal coursePrice;
    private String coursePicture;
}
