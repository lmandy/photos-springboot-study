package com.lmandy.service.impl;

import com.lmandy.bean.User;
import com.lmandy.dao.IBaseMapper;
import com.lmandy.dao.IUserMapper;
import com.lmandy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmandy on 2017/10/16.
 */
@Service
@Transactional
public class IUserServiceImpl extends IBaseServiceImpl<User> implements IUserService {

    @Autowired
    private IUserMapper userMapper;


    @Override
    public IBaseMapper getBaseMapper() {
        return userMapper;
    }

    @Override
    public User getUserByUserNameAndPassWord(String userName,String  passWord) {
        return userMapper.getUserByUserNameAndPassWord(userName,passWord);
    }
}
