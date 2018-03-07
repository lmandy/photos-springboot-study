package com.lmandy.service.impl;

import com.lmandy.bean.Image;
import com.lmandy.dao.IBaseMapper;
import com.lmandy.dao.IImgMapper;
import com.lmandy.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmandy on 2017/11/3.
 */
@Service
@Transactional
public class IImageServiceImpl extends IBaseServiceImpl<Image> implements IImageService {


    @Autowired
    private IImgMapper iImgMapper;

    @Override
    public IBaseMapper<Image> getBaseMapper() {
        return iImgMapper;
    }
}
