package com.lmandy.dao;

import com.lmandy.bean.Category;
import org.springframework.stereotype.Repository;

/**
 * Created by lmandy on 2017/11/2.
 */
@Repository
public interface ICategoryMapper extends IBaseMapper<Category>{

    void fileCategoryRelation(Integer fileId, Integer categoryId);
}
