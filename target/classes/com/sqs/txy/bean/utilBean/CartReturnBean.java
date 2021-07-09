package com.sqs.txy.bean.utilBean;

import com.sqs.txy.bean.utilBean.CartCourseInfo;
import lombok.Data;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-10 09:40
 **/

@Data
public class CartReturnBean {
    private Integer supporterId;
    private String courseSupporter;
    private List<CartCourseInfo> coursesInfo;
}
