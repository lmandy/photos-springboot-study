package com.lmandy.service;

import com.lmandy.utils.PageBean;

import java.util.List;

/**
 * Created by lmandy on 2017/11/3.
 */
public interface IBaseService<T> {
    /**
     * 获取指定记录
     * @param id
     * @return
     */
    T getById(Integer id);

    /**
     * 根据name获取指定信息
     * @param name
     * @return
     */
    T getByName(String name);

    /**
     * 获取所有
     * @return
     */
    List<T> getAll();
    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(Integer id);
    /**
     * 删除所有
     */
    Integer deleteAll();
    /**
     * 插入
     * @param t
     */
    void insert(T t);

    /**
     * 批量插入
     * @param tList
     */
    void batchInsert(List<T> tList);

    /**
     * 修改
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     *分页查询
     * @param pageBean
     * @return
     */
    void indexPageInfo(PageBean<T> pageBean);
}
