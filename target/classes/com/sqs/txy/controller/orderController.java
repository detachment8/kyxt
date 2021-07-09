package com.sqs.txy.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.*;
import com.sqs.txy.bean.utilBean.orderDetail;
import com.sqs.txy.mapper.OrderMapper;
import com.sqs.txy.mapper.UserMapper;
import com.sqs.txy.service.orderService;
import com.sqs.txy.service.studentService;
import com.sqs.txy.service.teacherService;
import com.sqs.txy.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-13 22:30
 **/

@RequestMapping("/order")
@Controller
public class orderController {

    @Autowired
    private userService userService;

    @Autowired
    private studentService studentService;

    @Autowired
    private teacherService teacherService;

    @Autowired
    private orderService orderService;

    @RequestMapping("/toPayOrder")
    public String toPayOrder(String orderNumber,Model model){
        System.out.println(orderNumber);
        orderDetail detail = orderService.findOrderDetailByNumber(orderNumber);
        model.addAttribute("order",detail);
        System.out.println(detail);
        return "/mall/payOff";
    }

    //查看我的订单
    @RequestMapping("/toMyOrder")
    public String toMyOrder(HttpServletRequest request, Model model){
        String username =(String) request.getSession().getAttribute("username");
        SysStudent student = studentService.findSysStudentByName(username);
        SysUser user = userService.findSysuserInfo(username);
        SysTeacher teacher = teacherService.findSysTeacherByName(username);
        model.addAttribute("studentInfo",student);
        model.addAttribute("teacherInfo",teacher);
        model.addAttribute("userInfo",user);
        return "/user/myOrder";
    }

    //获取订单全部的信息，渲染资料里的表格，带分页
    @ResponseBody
    @RequestMapping(value = "getOrders")
    public String getOrders(@RequestParam("username") String username
    ,@RequestParam("limit") Integer pageSize,Integer page) {
        System.out.println(pageSize);
        System.out.println(page);
        PageInfo<SysOrder> orders = orderService.findOrderByUserName(username,page,pageSize);
        System.out.println(orders);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", orders.getTotal());
        map.put("data", orders.getList());
        System.out.println(new JSONObject(map).toJSONString());
        return new JSONObject(map).toJSONString();
    }

    //查看订单详细情况
    @RequestMapping("/getDetail/{orderNumber}")
    public String getDetail(@PathVariable("orderNumber")String orderNumber, Model model){
        orderDetail orderDetail = orderService.findOrderDetailByNumber(orderNumber);
        model.addAttribute("orderDetail",orderDetail);
        System.out.println(orderDetail);
        return "/mall/orderDetail";
    }

    //完成订单支付
    @RequestMapping("/payOrder")
    public String payOrder(String orderNumber,HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int i = orderService.completeOrder(orderNumber, username);
        if ( i == 1){
            return "/mall/finishOrder";
        }else {
            return "/mall/failFinishOrder";
        }
    }
}
