package com.sqs.txy.bean.utilBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.bean.SysCourseOrder;
import com.sqs.txy.bean.SysOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-15 11:50
 **/

@Data
public class orderDetail {
    private Integer orderId;
    private String orderNumber;
    private Date orderCreateTime;
    private Date orderFinishTime;
    private Integer orderStatus;
    private BigDecimal orderPrice;
    private String userName;
    private String orderRestain;
    private List<SysCourseOrder> orderItem;
}
