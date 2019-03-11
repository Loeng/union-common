package com.union.validate.validator;



import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GaoWei
 * @describe
 * @time 2017/12/26,10:25
*/
public class IsDecimal extends BindingValidator {

    private String regex = "^([1-9]\\d*|[0-9]\\d*\\.[0-9]{1,2})$";

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (check.isDecimal()) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(value));
            return matcher.matches();
        }
        return true;
    }


}
