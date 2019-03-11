package com.union.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GaoWei
 * @describe 身份证验证
 * @time 2018/2/8,15:14
*/
public class IdCardUtil {

    /**
     * 使用正则表达式判断
     * @param idCard
     * @return
     */
    public static boolean verifyIdCard(String idCard){
        String strVerify = "(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|x|X)$)|(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)";
        Pattern p = Pattern.compile(strVerify);
        Matcher m = p.matcher(idCard);
        return m.find();
    }


    /**
     * 使用java代码验证
     * @param no
     * @return
     */
    public static boolean validate(String no)
    {
        // 对身份证号进行长度等简单判断
        if (no == null || no.length() != 18 || !no.matches("\\d{17}[0-9X]"))
        {
            return false;
        }
        // 1-17位相乘因子数组
        int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        // 18位随机码数组
        char[] random = "10X98765432".toCharArray();
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < 17; i++)
        {
            total += Character.getNumericValue(no.charAt(i)) * factor[i];
        }
        // 判断随机码是否相等
        return random[total % 11] == no.charAt(17);
    }

}
