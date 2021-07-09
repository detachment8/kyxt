package com.sqs.txy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sqs.txy.bean.utilBean.makeOrderEntity;
import com.sqs.txy.service.cartService;
import com.sqs.txy.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-10 21:06
 **/

@Controller
@RequestMapping("/cart")
public class cartController {
    @Autowired
    private cartService cartService;

    @Autowired
    private orderService orderService;

    @ResponseBody
    @RequestMapping("/courseClear")
    public Map<String,String> clear(@RequestParam("courseId") Integer courseId, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        Integer i = cartService.removeCourse(username, courseId);
        Map<String,String> map = new HashMap<>();
        map.put("status",String.valueOf(i));
        return map;
    }

    @RequestMapping("/clearAllCourses")
    public String clearAllCourses(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        cartService.removeAllCourse(username);
        return "redirect:/mall/toCart";
    }

    //{"orderCourseId":["2","1"],"orderAmounts":["1","1"]}
    @ResponseBody
    @RequestMapping("/MakeOrder")
    public Map<String,Object> MakeOrder(HttpServletRequest request,@RequestBody String JsonString){
        JSONObject parse = JSONObject.parseObject(JsonString);
        JSONArray orderCourseId = parse.getJSONArray("orderCourseId");
        JSONArray orderAmounts = parse.getJSONArray("orderAmounts");
        List<makeOrderEntity> list = new ArrayList<>();
        //将json字符串中数据去除
        for (int i = 0;i<orderAmounts.size();i++){
            int courseId = Integer.parseInt((String) orderCourseId.get(i));
            int orderAmount = Integer.parseInt((String) orderAmounts.get(i));
            list.add(new makeOrderEntity(courseId,orderAmount));
        }
            if (list.size() == 0 ){
                Map<String,Object> map =new HashMap<>();
                map.put("status",3);
                return map;
            }
        String username = (String) request.getSession().getAttribute("username");
        Map<String,Object> map = orderService.makeOrder(username, list);
        return map;
    }
}
