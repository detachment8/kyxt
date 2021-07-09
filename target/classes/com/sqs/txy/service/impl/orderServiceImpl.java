package com.sqs.txy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.SysOrder;
import com.sqs.txy.bean.utilBean.makeOrderEntity;
import com.sqs.txy.bean.utilBean.orderDetail;
import com.sqs.txy.mapper.CartMapper;
import com.sqs.txy.mapper.CourseMapper;
import com.sqs.txy.mapper.OrderMapper;
import com.sqs.txy.mapper.relationMapper;
import com.sqs.txy.myUtils.UUIDForOrder;
import com.sqs.txy.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-13 09:49
 **/

@Service
public class orderServiceImpl implements orderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private relationMapper relationMapper;

    //生成订单，由于事务的原则，生成订单和将订单的商品加入要分两部
    @Override
    public String createOrder(String userName, List<makeOrderEntity> list) {
        BigDecimal totalPrice =new BigDecimal("0");
        String orderNumber =UUIDForOrder.MyUUIDForOrder();
        for (makeOrderEntity item:list){
            Integer courseId = item.getOrderCourseId();
            Integer amount = item.getOrderAmount();
            BigDecimal price = courseMapper.findCourseById(courseId).getCoursePrice();
            BigDecimal multiply = price.multiply(BigDecimal.valueOf(amount));
            totalPrice = totalPrice.add(multiply);
        }
        SysOrder sysOrder = new SysOrder();
        sysOrder.setOrderCreateTime(new Date());
        sysOrder.setOrderNumber(orderNumber);
        sysOrder.setOrderPrice(totalPrice);
        sysOrder.setOrderStatus(0);
        sysOrder.setUserName(userName);
        int i = orderMapper.createNewOrder(sysOrder);
        //这是一个事务，所有的步骤都实现了，才能保存，不然就要回滚
        return orderNumber;
    }

    @Override
    public int addOrderCourses(List<makeOrderEntity> list,String orderNumber) {
        int flag = 1;

        for (makeOrderEntity item:list){
          flag =flag & orderMapper.addCourseToOrder(orderNumber,item.getOrderCourseId(),item.getOrderAmount());
        }
        return flag;
    }

    @Transactional
    @Override
    public Map<String,Object> makeOrder(String userName, List<makeOrderEntity> list) {
        Map<String, Object> map = new HashMap<>();
        BigDecimal totalPrice = new BigDecimal("0");
        String orderNumber = UUIDForOrder.MyUUIDForOrder();
        map.put("orderNumber", orderNumber);
        for (makeOrderEntity item : list) {
            Integer courseId = item.getOrderCourseId();
            Integer amount = item.getOrderAmount();
            BigDecimal price = courseMapper.findCourseById(courseId).getCoursePrice();
            BigDecimal multiply = price.multiply(BigDecimal.valueOf(amount));
            totalPrice = totalPrice.add(multiply);
        }
        SysOrder sysOrder = new SysOrder();
        sysOrder.setOrderCreateTime(new Date());
        sysOrder.setOrderNumber(orderNumber);
        sysOrder.setOrderPrice(totalPrice);
        sysOrder.setOrderStatus(0);
        sysOrder.setUserName(userName);
        int i = orderMapper.createNewOrder(sysOrder);
        //这是一个事务，所有的步骤都实现了，才能保存，不然就要回滚
        if (i == 1) {
            for (makeOrderEntity item : list) {
                i = i & orderMapper.addCourseToOrder(orderNumber, item.getOrderCourseId(), item.getOrderAmount());

            }
        }
        if (i == 0){
            map.replace("orderNumber",null);
        }
        map.put("status",i);
        for (makeOrderEntity item : list) {
            cartMapper.removeCourse(userName,item.getOrderCourseId());
        }
        return map;
    }

    @Override
    public PageInfo<SysOrder> findOrderByUserName(String username,Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<SysOrder> all = orderMapper.findOrderByUserName(username);
        PageInfo<SysOrder> info = new PageInfo(all);
        return info;
    }

    @Override
    public orderDetail findOrderDetailByNumber(String orderNumber) {
        return orderMapper.findOrderDetailByNumber(orderNumber);
    }

    @Transactional
    @Override
    public int completeOrder(String orderNumber,String userName) {
        int flag = 1;
        flag = orderMapper.updateOrderStatu(orderNumber,new Date(),1);
        List<Integer> courseIds = orderMapper.findAllCourseIdByOrderNumber(orderNumber);
        for (Integer courseId:courseIds){
            flag = flag & relationMapper.addCourseForUser(userName,courseId);
        }
            return flag;
    }
}
