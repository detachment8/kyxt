package com.sqs.txy.service;
import com.sqs.txy.bean.utilBean.CartReturnBean;
import java.util.List;

public interface cartService {
    //判断购物车是否已存在一个商品
    public int isExistInCart(String userName,Integer courseId);
    //在购物车中添加新的商品
    public int addCourseToCart(String userName,Integer courseId);
    //查出购物车中的所有商品和信息
    public List<CartReturnBean> findCartInfo(String userName);
    //移除购物车中的一个商品
    public int removeCourse(String userName,Integer courseId);
    //移除购物车的全部商品
    public  int removeAllCourse(String userName);

}
