package com.union.common.utils.base;

public interface SpringCloudBaseService<T> {
    int addObj(T t);

    int deleteObjById(int id);

    int modifyObj(T t);

    T queryObjById(int id);
}
