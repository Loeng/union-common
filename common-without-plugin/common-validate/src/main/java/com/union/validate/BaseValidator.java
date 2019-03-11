package com.union.validate;


import com.union.validate.annotation.Check;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:32
*/
public interface BaseValidator {

    void validate(Check check, String fieldName, Object value);

}
