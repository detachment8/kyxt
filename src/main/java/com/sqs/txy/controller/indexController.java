package com.sqs.txy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-01 12:23
 **/
@Controller
public class indexController {

  /*  @RequestMapping("/toIndex")
    public  String toIndex(Model model,HttpServletResponse response, HttpServletRequest request){
        System.out.println("txytxytxy!!!!!!!!!!");
        HttpSession session = request.getSession();
        model.addAttribute("username",session.getAttribute("username"));
        System.out.println((String) session.getAttribute("username"));
        System.out.println(session.getId());
        return "redirect:/";
    }*/

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
}
