package com.union.common.utils.create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author GaoWei
 * @describe 创建订单编号类
 * 允许每毫秒生成最多99张订单
 * @time 2017/12/19,14:46
*/
public class CreateOrderNo {

    /**
     * 私有构造器
     */
    private CreateOrderNo(){};

    private static CreateOrderNo order=null;

    /**
     * 单例模式--懒汉模式
     * @return
     */
    public static synchronized CreateOrderNo getInstance() {
        if (order == null) {
            order = new CreateOrderNo();
        }
        return order;
    }

    /**
     * 全局自增数
     */
    private static int count = 0;

    /**
     * 每毫秒秒最多生成多少订单（最好是像99这种准备进位的值）
     */
    private static final int total = 99;

    /**
     * 格式化的时间字符串
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 获取当前时间年月日时分秒毫秒字符串
     * @return
     */
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    /**
     * 记录上一次的时间，用来判断是否需要递增全局数
     */
    private static String now = null;

    /**
     * 生成一个订单号
     */
    public synchronized String GenerateOrder() {
        String datastr = getNowDateStr();
        if (datastr.equals(now)) {
            count++;
        } else {
            count = 1;
            now = datastr;
        }
        int countInteger = String.valueOf(total).length() - String.valueOf(count).length();
        String bu = "";
        for (int i = 0; i < countInteger; i++) {
            bu += "0";
        }
        bu += String.valueOf(count);
        if (count >= total) {
            count = 0;
        }
        StringBuilder sb=new StringBuilder();
        sb.append(datastr);
        sb.append(bu);
        sb.append(createRandomString());
        return sb.toString();
    }

    /**
     * 创建5位随机数字
     * @return
     */
    private static String createRandomString(){
        Random random = new Random();
        String result="";
        for (int i=0;i<5;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println(CreateOrderNo.getInstance().GenerateOrder().length());
    }

}
