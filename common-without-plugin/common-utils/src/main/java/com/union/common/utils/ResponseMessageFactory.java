package com.union.common.utils;

/**
 * Created by GaoWei on 2017/9/11.
 */
public class ResponseMessageFactory {
    private static ResponseMessage responseMessage;
    private ResponseMessageFactory(){}
    public static ResponseMessage newInstance(){
        if(responseMessage==null){
            return new ResponseMessage();
        }
        return responseMessage;
    }
}
