package com.lmandy.service.impl;

import com.lmandy.bean.Category;
import com.lmandy.dao.IBaseMapper;
import com.lmandy.dao.ICategoryMapper;
import com.lmandy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmandy on 2017/11/22.
 */
@Service
@Transactional
public class ICategoryServiceImpl extends IBaseServiceImpl<Category> implements ICategoryService {

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public IBaseMapper getBaseMapper() {

        return categoryMapper;
    }
    @Override
    public void fileCategoryRelation(Integer fileId,Integer categoryId){
        categoryMapper.fileCategoryRelation(fileId,categoryId);
    }
}
