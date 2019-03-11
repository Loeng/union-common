package com.union.mongo;

import com.github.pagehelper.PageInfo;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * mongoDB 操作基础类
 *
 * @author czm
 * @version 1.0
 * @create 2018-08-27 14:20
 **/
public interface BaseMongoDao<T> {

    boolean removeById(String id);

    T getById(String id);

    void insert(T t);

    boolean updateById(Update update, String id);

    PageInfo<T> findByPageForFront(Integer pageNo, Integer pageSize, Query query);

    PageInfo<T> findByPageForFront(Integer pageNo, Integer pageSize, Query query, String collectionName);

}
