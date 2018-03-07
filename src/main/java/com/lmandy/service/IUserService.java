package com.lmandy.service;

import com.lmandy.bean.User;

/**
 * Created by lmandy on 2017/10/16.
 */
public interface IUserService extends IBaseService<User>{

    User getUserByUserNameAndPassWord(String userName,String passWord);

}
