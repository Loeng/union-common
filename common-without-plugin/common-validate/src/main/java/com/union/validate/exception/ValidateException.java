package com.union.validate.exception;


import com.union.validate.BindResult;
import org.apache.commons.lang3.StringUtils;

/**
 * @author GaoWei
 * @describe
 * @time 2017/12/26,10:23
 */
public class ValidateException extends RuntimeException {

    private BindResult bindResult;

    public ValidateException(BindResult bindResult) {
        super(bindResult.getMessage());
        this.bindResult = bindResult;
    }

    public ValidateException(String field, Object validValue, String message) {
        this.bindResult = new BindResult(field, validValue, message);
    }
    public BindResult getBindResult() {
        return bindResult;
    }
}
