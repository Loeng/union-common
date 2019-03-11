package com.union.common.utils.aop;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.union.common.utils.ResponseException;
import com.union.common.utils.ResponseMessage;
import com.union.common.utils.exception.ClientException;
import com.union.common.utils.exception.ServerException;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;


@ControllerAdvice
public class GloadExceptionHandler {


    /**
     * 捕获微信支付异常,返回给微信
     * @param
     * @param ex
     * @return
     */
    @ExceptionHandler(WxErrorException.class)
    @ResponseBody
    public WxPayNotifyResponse handleWxErrorException(WxErrorException ex) {
        WxPayNotifyResponse wxPayNotifyResponse=new WxPayNotifyResponse();
        wxPayNotifyResponse.setReturnCode("FAIL");
        wxPayNotifyResponse.setReturnMsg(ex.getError().getErrorMsg());
        return wxPayNotifyResponse;
    }

    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    public ResponseMessage handlerResponseException(ResponseException ex) {
        return new ResponseMessage(ex.getCode(), ex.getMessage());
    }


    /**
     * 事务控制器tx-manager异常
     * @return
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseBody
    public ResponseMessage handlerUndeclaredThrowableException(){
        return new ResponseMessage(500,"服务器网络不稳定,请稍后重试");
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public ResponseMessage handlerClientException(ClientException ex) {
        return new ResponseMessage(ex.getCode(), ex.getMsg());
    }



    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ResponseMessage handServerException(ServerException s){
        String msg=s.getMsg();
        return new ResponseMessage(s.getCode(), msg);
    }









}
