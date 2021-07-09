package com.sqs.txy.service.impl;

import com.sqs.txy.bean.utilBean.CartReturnBean;
import com.sqs.txy.bean.SysCartInfo;
import com.sqs.txy.mapper.CartMapper;
import com.sqs.txy.myUtils.CartInfoConverter;
import com.sqs.txy.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-09 22:24
 **/

@Service
public class cartServiceImpl implements cartService {
    @Autowired
    private CartMapper cartMapper;
    //判断购物车是否已存在一个商品
    @Override
    public int isExistInCart(String userName, Integer courseId) {
        return cartMapper.isExistInCart(userName,courseId);
    }

    //在购物车中添加新的商品,0失败，1成功
    @Override
    public int addCourseToCart(String userName, Integer courseId) {
        int flag = cartMapper.isExistInCart(userName, courseId);
        if (flag==1){
            return 0;
        }else{
            flag = cartMapper.addCourseToCart(userName,courseId);
        }
        return flag;
    }

    @Override
    public List<CartReturnBean> findCartInfo(String userName) {
        List<SysCartInfo> cartInfo = cartMapper.findCartInfo(userName);
        return    CartInfoConverter.coursesInfoToCartInfo(cartInfo);
    }

    @Override
    public int removeCourse(String userName, Integer courseId) {
        return cartMapper.removeCourse(userName,courseId);
    }

    @Override
    public int removeAllCourse(String userName) {
        return cartMapper.removeAllCourse(userName);
    }


}
