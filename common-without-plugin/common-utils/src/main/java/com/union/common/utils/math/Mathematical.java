package com.union.common.utils.math;

import java.math.BigDecimal;

/**
 * @author GaoWei
 * @describe 数学计算类
 * @time 2017/12/19,17:18
*/
public class Mathematical {

    /**
     * 获取int相除后保留两位小数
     * @param top
     * @param below
     * @return
     */
    public static double deciMal(int top, int below) {
        double result = new BigDecimal((float)top / below).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }
}
