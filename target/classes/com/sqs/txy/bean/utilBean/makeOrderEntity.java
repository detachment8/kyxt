package com.sqs.txy.bean.utilBean;

import lombok.Data;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-12 21:07
 **/

@Data
public class makeOrderEntity {
    private Integer orderCourseId;
    private Integer orderAmount;

    public makeOrderEntity(){

    }

    public makeOrderEntity(Integer orderCourseId,Integer orderAmount){
        this.orderCourseId = orderCourseId;
        this.orderAmount = orderAmount;
    }
}
