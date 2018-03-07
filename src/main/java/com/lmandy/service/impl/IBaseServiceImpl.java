package com.lmandy.service.impl;

import com.lmandy.dao.IBaseMapper;
import com.lmandy.service.IBaseService;
import com.lmandy.utils.PageBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lmandy on 2017/11/3.
 */
public abstract class IBaseServiceImpl<T> implements IBaseService<T>{

    public abstract IBaseMapper<T> getBaseMapper();

    private Class<T> clazz;

    public IBaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;

        Type[] types = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    @Override
    public T getById(Integer id) {
        return getBaseMapper().getById(id);
    }

    @Override
    public T getByName(String name) {
        return getBaseMapper().getByName(name);
    }

    @Override
    public List<T> getAll() {
        return getBaseMapper().getAll();
    }

    @Override
    public Integer delete(Integer id) {
        return getBaseMapper().delete(id);
    }

    @Override
    public Integer deleteAll() {
        return getBaseMapper().deleteAll();
    }

    @Override
    public void insert(T t) {
        getBaseMapper().insert(t);
    }

    @Override
    public void batchInsert(List<T> tList) {
        getBaseMapper().batchInsert(tList);
    }

    @Override
    public Integer update(T t) {
        return getBaseMapper().update(t);
    }

    @Override
    public void indexPageInfo(PageBean<T> pageBean) {
        pageBean.setTotalResults(getBaseMapper().getTotalResult(pageBean));
        pageBean.setTotalCount(getBaseMapper().getTotalCount(pageBean));
    }
}
