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
public class IdCard extends BindingValidator {

    private String regex = "^\\d{15}$|^\\d{17}[\\d,x,X]{1}$";

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (check.idCard()) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(value));
            return matcher.matches();
        }
        return true;
    }

}
