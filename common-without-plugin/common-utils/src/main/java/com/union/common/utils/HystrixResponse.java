package com.union.common.utils;

/**
*@author GaoWei
*descrption:
*time  2018/1/14 16:51
*/
public class HystrixResponse {

    /**
     * 远程调用失败，Hystrix返回
     * @return
     */
    public static ResponseMessage invokeFail(){
        ResponseMessage responseMessage=ResponseMessageFactory.newInstance();
        responseMessage.setCode(20001);
        responseMessage.setMessage("服务器或网络不稳定，请稍后再试");
        return responseMessage;
    }
}
