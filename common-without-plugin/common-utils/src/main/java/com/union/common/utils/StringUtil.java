package com.union.common.utils;

/**
 * @author GaoWei
 * @describe 常用字符串操作及判断
 * @time 2017/12/27,17:03
*/
public class StringUtil {

    /**
     * 三木运算，当传入值为null时替换为""，避免空指针
     * @return
     */
    public static String trimNull(String str){
        return str==null?"":str;
    }
}
