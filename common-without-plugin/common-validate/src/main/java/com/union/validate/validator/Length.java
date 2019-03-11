package com.union.validate.validator;


import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;

/**
 * @author GaoWei
 * @describe
 * @time 2017/12/26,10:25
*/
public class Length extends BindingValidator {

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (check.length() > 0 && String.valueOf(value).length() < check.length()) {
            this.msg = "#name#长度不能小于" + check.length();
            return false;
        } else {
            return true;
        }
    }
}
