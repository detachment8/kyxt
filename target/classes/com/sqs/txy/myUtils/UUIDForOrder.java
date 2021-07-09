package com.sqs.txy.myUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: kyxt
 * @author: sqs
 * @create: 2020-04-13 13:09
 **/

public class UUIDForOrder {

        public  static String MyUUIDForOrder(){
            // 1.开头四位，标识业务代码或机器代码（可变参数）
            String machineId = "0608";
            // 2.中间四位整数，标识日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String dayTime = sdf.format(new Date());
            // 3.生成uuid的hashCode值
            int hashCode = UUID.randomUUID().toString().hashCode();
            // 4.可能为负数
            if(hashCode < 0){
                hashCode = -hashCode;
            }
            // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
            String value = machineId + dayTime + String.format("%010d", hashCode);
            return value;
        }
}
