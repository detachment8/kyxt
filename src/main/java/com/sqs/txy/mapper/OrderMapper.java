package com.sqs.txy.mapper;

import com.sqs.txy.bean.SysCourseOrder;
import com.sqs.txy.bean.SysOrder;
import com.sqs.txy.bean.utilBean.orderDetail;
import org.apache.ibatis.annotations.*;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    //通过订单id查询订单
    @Select("select * from sys_order where order_id=#{orderId}")
    public SysOrder findOrderById(Integer orderId);

    //通过订单流水号查询订单商品
    @Select("select * from  sys_course_order where order_number=#{orderNumber}")
    public SysCourseOrder findOrderCourseByNumber(String orderNumber);

    //通过用户名查询所有的订单查询订单
    @Select("select * from sys_order where user_name=#{username}")
    public List<SysOrder> findOrderByUserName(String username);

    //通过订单流水号查询订单商品加上商品信息
    @Select("select * from  sys_course_order where order_number=#{orderNumber}")
    @Results({
            @Result(column = "order_number",property = "orderNumber"),
            @Result(column = "course_id",property = "courseId"),
            @Result(column = "course_id",property = "sysCourses",
            one = @One(select = "com.sqs.txy.mapper.CourseMapper.findCourseById")),
            @Result(column = "items_amount",property = "itemsAmount")
    })
    public SysCourseOrder findOrderCourseDetailByNumber(String orderNumber);

    //新建一个订单
    //INSERT INTO `myproject`.`sys_order`
    // (`order_number`, `order_create_time`, `order_status`, `order_price`, `user_name`, `order_restain`)
    // VALUES ('2', '2020-04-09 15:19:59', 1, 123, 'student', '1')
    @Insert("INSERT INTO `myproject`.`sys_order`" +
            "(`order_number`, `order_create_time`, `order_status`, `order_price`, `user_name`, `order_restain`) " +
            "VALUES (#{orderNumber},#{orderCreateTime}, 0, #{orderPrice}, #{userName}, #{orderRestain})")
    public int createNewOrder(SysOrder sysOrder);

    //给一个订单添加商品
    @Insert("INSERT INTO `myproject`.`sys_course_order`" +
            "(`order_number`, `course_id`, `items_amount`) " +
            "VALUES (#{orderNumber}, #{courseId}, #{itemsAmount})")
    public int addCourseToOrder(String orderNumber,Integer courseId,Integer itemsAmount);

    //返回订单详细信息
    @Select("select * from sys_order where order_number=#{orderNumber}")
    @Results({
            @Result(column = "order_number",property = "orderNumber"),
            @Result(column = "order_id" ,property = "orderId"),
            @Result(column = "order_create_time",property = "orderCreateTime"),
            @Result(column = "order_finish_time",property = "orderFinishTime"),
            @Result(column = "order_status",property = "orderStatus"),
            @Result(column = "order_price",property = "orderPrice"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "order_restain",property = "orderRestain"),
            @Result(column = "order_number",property = "orderItem",
            many = @Many(select = "com.sqs.txy.mapper.OrderMapper.findOrderCourseDetailByNumber"))
    }
    )
    public orderDetail findOrderDetailByNumber(String orderNumber);

    //将订单的状态修改为已完成
    @Update("UPDATE `myproject`.`sys_order` SET `order_status` = #{status} , `order_finish_time` =#{orderFinishTime} WHERE " +
            "`order_number` = #{orderNumber}")
    public int updateOrderStatu(String orderNumber, Date orderFinishTime,Integer status);

    //找到一个课程的所有course_id
    @Select("select course_id from sys_course_order where order_number=#{orderNumber}")
    public List<Integer> findAllCourseIdByOrderNumber(String orderNumber);
}
