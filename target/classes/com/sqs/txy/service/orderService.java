package com.sqs.txy.service;

import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.SysOrder;
import com.sqs.txy.bean.utilBean.makeOrderEntity;
import com.sqs.txy.bean.utilBean.orderDetail;

import java.util.List;
import java.util.Map;

public interface orderService {

    //生成订单,同时返回订单id
    public  String createOrder(String userName, List<makeOrderEntity> list);

    //为订单添加商品
    public  int addOrderCourses(List<makeOrderEntity> list,String orderNumber);

    //完成生成订单的全部炒作
    public Map<String,Object> makeOrder(String userName, List<makeOrderEntity> list);

    //查询所有订单信息
    public PageInfo<SysOrder> findOrderByUserName(String username, Integer page, Integer pageSize);

    //通过订单流水号查询该订单的所有信息
    public orderDetail findOrderDetailByNumber(String orderNumber);

    //完成订单,同时在状态表中添加用户和课程
    public  int completeOrder(String orderNumber,String userName);

}
