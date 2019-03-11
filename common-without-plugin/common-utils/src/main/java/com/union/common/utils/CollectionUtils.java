package com.union.common.utils;

import java.util.Collection;

/**
 * @author GaoWei
 * @describe 集合操作工具类
 * @time 2017/12/27,15:59
*/
public class CollectionUtils {

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection==null||collection.size()==0;
    }
}
