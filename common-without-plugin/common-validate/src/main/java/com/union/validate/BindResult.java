package com.union.validate;


/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:32
*/
public class BindResult {

    private String field;
    private Object validValue;
    private String message;

    public BindResult(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public BindResult(String field, Object validValue, String message) {
        this.field = field;
        this.validValue = validValue;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValidValue() {
        return validValue;
    }

    public void setValidValue(Object validValue) {
        this.validValue = validValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
