package com.sqs.txy.controller;

import com.github.pagehelper.PageInfo;
import com.sqs.txy.bean.utilBean.CartReturnBean;
import com.sqs.txy.bean.SysCourse;
import com.sqs.txy.bean.utilBean.makeOrderEntity;
import com.sqs.txy.service.cartService;
import com.sqs.txy.service.courseService;
import com.sqs.txy.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-05 15:40
 **/

@RequestMapping("/mall")
@Controller
public class mallController {
    @Autowired
    private courseService courseService;
    @Autowired
    private cartService cartService;
    @Autowired
    private orderService orderService;

    @RequestMapping("/toMall")
    public String toMall(){
        return "/mall/mallIndex";
    }


    @RequestMapping("/mallSearch")
    public String mallSearch(String searchItem,@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                             Model model){
        PageInfo<SysCourse> courses = courseService.findSearchCourse(searchItem, currentPage, 12);
        System.out.println(courses);
        model.addAttribute("courses",courses);
        model.addAttribute("searchItem",searchItem);
        return "/mall/mallSearch";
    }

    @RequestMapping("/courseDetail")
    public String courseDetail(@RequestParam(value = "courseId",defaultValue = "1")Integer courseId
                                                    ,Model model){
        SysCourse course = courseService.findCourseById(courseId);
        model.addAttribute("course",course);
        return "/mall/courseDetail";
    }

    @RequestMapping("/toCart")
    public String toCart(HttpServletRequest request,Model model){
        String userName = (String) request.getSession().getAttribute("username");
        List<CartReturnBean> cartInfo = cartService.findCartInfo(userName);
        model.addAttribute("cartInfo",cartInfo);
        return "/mall/cart";
    }

    //加入购物车
    @ResponseBody
    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("courseId") Integer courseId, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        if (username == null){
            return "{\"status\":0}";
        }
        int flag = cartService.addCourseToCart(username, courseId);
        if (flag==0){
            return "{\"status\":1}";
        }else {
            return "{\"status\":2}";
        }

    }

    @RequestMapping("/buyCourse")
    public String buyCourse(String courseId,HttpServletRequest request,Model model
    ,RedirectAttributes attr){
        String userName = (String) request.getSession().getAttribute("username");
        if (userName == null){
            model.addAttribute("msg","请先登录！");
            return "login";
        }
        System.out.println(courseId);
        List<makeOrderEntity> list = new ArrayList<>();
        list.add(new makeOrderEntity(Integer.valueOf(courseId),1));
        Map<String, Object> map = orderService.makeOrder(userName, list);
        String orderNumber = (String)map.get("orderNumber");
        System.out.println(orderNumber);
        if (orderNumber!=null){
            attr.addAttribute("orderNumber",orderNumber);
        }else {
            return "/mall/failFinishOrder";
        }
        return "redirect:/order/toPayOrder";
    }

}
