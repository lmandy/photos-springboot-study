package com.lmandy.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lmandy.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by lmandy on 2017/10/20.
 * 可以在每个mapper上级@Mapper
 * 亦可以在项目启动的入口程序上加扫描 @MapperScan("com.lmandy.dao")
 */
@Repository
public interface IUserMapper extends IBaseMapper<User>{

    @Override
    User getById(Integer id);

    @Select("select * from img_user where username = #{_parameter}")
    @Override
    User getByName(String name);

    @Insert("insert into img_user(id,username,password) value(NULL,#{userName},#{passWord})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Override
    void insert(User user);
    /**
     * 根据用户名和密码获取用户
     * @param userName
     * @param passWord
     * @return
     */
    @Select("select * from img_user where username = #{userName} and password = #{passWord}")
    User getUserByUserNameAndPassWord(@Param("userName") String userName,@Param("passWord") String passWord);

    @Delete("delete from img_user where id = ${_parameter}")
    @Override
    Integer delete(Integer id);
}
