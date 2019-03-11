package com.union.common.utils;

import com.union.common.utils.constant.ResponseContants;

/**
 * 返回结果
 *
 * @author liurenkai
 * @time 2018/4/11 19:48
 */
public class ResponseUtil {

    /**
     * 是否成功
     *
     * @param responseMessage 返回
     * @return
     */
    public static boolean isSuccess(ResponseMessage responseMessage) {
        return ResponseContants.SUCCESS == responseMessage.getCode();
    }

    /**
     * 是否失败
     *
     * @param responseMessage 返回
     * @return
     */
    public static boolean isFail(ResponseMessage responseMessage) {
        return ResponseContants.SUCCESS != responseMessage.getCode();
    }

    /**
     * 是否成功，如果成功，抛出异常
     *
     * @param responseMessage
     * @return
     */
    public static void isSuccessThrowException(ResponseMessage responseMessage) {
        if (isSuccess(responseMessage)) {
            throw new ResponseException(responseMessage);
        }
    }

    /**
     * 是否失败，如果失败，抛出异常
     *
     * @param responseMessage 返回
     * @return
     */
    public static void isFailThrowException(ResponseMessage responseMessage) {
        if (isFail(responseMessage)) {
            throw new ResponseException(responseMessage);
        }
    }

}
