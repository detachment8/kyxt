package com.sqs.txy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface autMapper {

    @Insert("insert into sys_user_aut values(#{username},#{pictureLocation},0)")
    public  int addAutInfo(String username,String pictureLocation);
}
