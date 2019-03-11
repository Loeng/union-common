package com.union.common.utils.base;

import com.union.common.utils.ResponseMessage;

/**
 * Created by GaoWei on 2017/9/7.
 */
public interface BaseService<T> {
     ResponseMessage<T> addObj(T t);

     ResponseMessage<T> deleteObjById(int id);

     ResponseMessage<T> modifyObj(T t);

     ResponseMessage<T> queryObjById(int id);

}
