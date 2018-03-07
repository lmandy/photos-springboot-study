package com.lmandy.service.impl;

import com.lmandy.bean.Joke;
import com.lmandy.dao.IBaseMapper;
import com.lmandy.dao.IJokeMapper;
import com.lmandy.service.IJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmandy on 2017/11/3.
 */
@Service
@Transactional
public class IJokeServiceImpl extends IBaseServiceImpl<Joke> implements IJokeService{

    @Autowired
    private IJokeMapper jokeMapper;


    @Override
    public IBaseMapper<Joke> getBaseMapper() {
        return jokeMapper;
    }
}
