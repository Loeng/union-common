package com.union.common.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author GaoWei
 * @describe 生成随机字符类
 * @time 2017/12/9,18:15
*/
public class RandomUtil {

    /**
     * 生成4位随机字符
     * @return
     */
    public static  String verificationCode() {
        String random = RandomStringUtils.randomNumeric(4);
        return random;
    }

}
