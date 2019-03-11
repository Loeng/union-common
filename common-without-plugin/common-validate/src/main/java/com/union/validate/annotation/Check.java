package com.union.validate.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:24
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Check {
    /**
     * 映射参数名称 @Check(value = “name”) String username
     * @return
     */
    String value() default "";

    /**
     * 是否必填，默认为必填
     * @return
     */
    boolean required() default true;

    /**
     * 默认值
     * @return
     */
    String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";

    /**
     * 允许数字最小值，支持小数 1
     * @return
     */
    String min() default "";

    /**
     * 允许数字最大值，支持小数 10
     * @return
     */
    String max() default "";

    /**
     * 自定义正则表达式 ^[A-Za-z]+$
     * @return
     */
    String pattern() default "";

    /**
     *  提示字段名，返回message中体现
     * @return
     */
    String name() default "";

    /**
     * 校验是否是手机号
     * @return
     */
    boolean mobile() default false;

    /**
     * 校验是否是邮箱
     * @return
     */
    boolean email() default false;

    /**
     * 校验是否是身份证
     * @return
     */
    boolean idCard() default false;

    /**
     *校验是否最多两位小数，可用作money
     * @return
     */
    boolean isDecimal() default false;

    /**
     *  校验是否只包含数字和字母
     * @return
     */
    boolean numOrLetter() default false;

    /**
     *校验是否中文
     * @return
     */
    boolean chinese() default false;

    /**
     * ip boolean 校验是否IP格式
     * @return
     */
    boolean ip() default false;

    /**
     * 校验是否URL格式
     * @return
     */
    boolean url() default false;

    /**
     * 校验是否日期 2012-12-23 23:23:23
     * @return
     */
    boolean date() default false;

    /**
     * 校验是否数字
     * @return
     */
    boolean number() default false;

    /**
     * 校验是否数字，且不为0
     * @return
     */
    boolean notZero() default false;

    /**
     * 校验是否字母
     * @return
     */
    boolean letter() default false;

    /**
     * 校验字符串长度
     * @return
     */
    int length() default 0;

    /**
     * 自定义校验器 下边举例说明
     * 自定义校验器:
     * 继承BaseValidator 接口，实现 validate方法
     * public class TestValidator implements BaseValidator{
     * @Override public void validate(Check check, String s, Object o) {
     * System.out.println(check);
     * System.out.println(s);
     * System.out.println(o); } }
     * @Check(valid = TestValidator.class) String self
     * 其中 valid为数组，可以添加N个校验器
     * validate方法中参数：
     * check为当前参数注解对象可以获取所需信息。
     * s为当前参数名称。
     * o为当前参数值。
     * 校验失败请自行抛出异常处理。
     * 校验失败时，可以拦截ValidateException类型异常，
     * 可以通过异常获取BindResult对象，并从对象中拿到校验失败时的参数名，参数值及message。
     * @return
     */
    Class[] valid() default {};

}
