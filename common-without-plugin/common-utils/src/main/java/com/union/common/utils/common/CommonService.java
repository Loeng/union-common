package com.union.common.utils.common;

import com.union.common.utils.ResponseMessage;

import java.util.List;

/**
 * 通用mapper
 *
 * @author liurenkai
 * @time 2018/8/1 16:47
 */
public interface CommonService<T> {

    /**
     * 新增对象
     *
     * @param t 对象
     * @return
     */
    ResponseMessage<T> add(T t);

    /**
     * 根据ID删除对象
     *
     * @param id ID
     * @return
     */
    ResponseMessage<Integer> removeById(Integer id);

    /**
     * 根据ID更新对象
     *
     * @param t 对象
     * @return
     */
    ResponseMessage<Integer> updateById(T t);

    /**
     * 根据ID查询对象
     *
     * @param id ID
     * @return
     */
    ResponseMessage<T> getById(Integer id);

}
