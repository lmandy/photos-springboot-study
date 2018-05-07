package com.lmandy.service;

import com.lmandy.bean.Category;

/**
 * Created by lmandy on 2017/11/22.
 */
public interface ICategoryService extends IBaseService<Category>{

    void fileCategoryRelation(Integer fileId,Integer categoryId);
    void deleteRelation(Integer fileId);

}
