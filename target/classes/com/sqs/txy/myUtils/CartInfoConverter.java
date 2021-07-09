package com.sqs.txy.myUtils;

import com.sqs.txy.bean.utilBean.CartCourseInfo;
import com.sqs.txy.bean.utilBean.CartReturnBean;
import com.sqs.txy.bean.SysCartInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-10 09:48
 **/

public class CartInfoConverter {

    //将后台的数据结果集转化为前端可以展示的链表
    //将原本一条条的数据转化为教师的名字和id+所有该教师的课程
    public  static List<CartReturnBean> coursesInfoToCartInfo(List<SysCartInfo> list){

        List<CartReturnBean>  returnList= new ArrayList<>();
        for (SysCartInfo item:list){
            //判断当前购物车项目的提供者是否已存在于数组中
            int flag = 0;
            int i = 0;
        for ( i = 0;i<returnList.size();i++){
                if (returnList.get(i).getSupporterId() == item.getSupporterId()){
                    flag = 1;
                    break;
                }
        }
        //如果已经存在这个老师
                if (flag == 1){
                    int CartReturnBean_list_flag = 0;
                    CartReturnBean bean = returnList.get(i);
                    List<CartCourseInfo> info = bean.getCoursesInfo();
                    //将当前项的信息注入CartCourseInfo中，进而加入数组中
                    CartCourseInfo courseInfo = new CartCourseInfo(item);
                    //判断该老师的课程数组中是否有该课程，不存在则添加新的
                    for ( int k = 0;k<info.size();k++){
                        if (info.get(k).getCourseId() == item.getCourseId()){
                            CartReturnBean_list_flag = 1;
                            CartCourseInfo courseInfo1 = info.get(k);
                            courseInfo1.setCourseAmount(courseInfo1.getCourseAmount()+1);
                            break;
                        }

                    }

                    if ( CartReturnBean_list_flag ==0){
                        info.add(courseInfo);
                    }
                }
                else {
                    CartReturnBean cartReturnBean = new CartReturnBean();
                    SysCartInfo sysCartInfo = list.get(i);
                    cartReturnBean.setSupporterId(item.getSupporterId());
                    cartReturnBean.setCourseSupporter(item.getCourseSupporter());
                    cartReturnBean.setCoursesInfo( new ArrayList<CartCourseInfo>()) ;

                    CartCourseInfo courseInfo = new CartCourseInfo(item);
                    cartReturnBean.getCoursesInfo().add(courseInfo);
                    returnList.add(cartReturnBean);
                }
        }
        return returnList;
    }
}
