package com.sqs.txy.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-12 10:35
 **/

@Data
public class SysOrder {
    private Integer orderId;
    private String orderNumber;
    @JSONField(format = "yyyy-MM-dd h:mm:ss")
    private Date orderCreateTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date orderFinishTime;
    private Integer orderStatus;
    private BigDecimal orderPrice;
    private String userName;
    private String orderRestain;
}
