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
public class IP extends BindingValidator {

    private String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    private String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (check.ip()) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(value));
            return matcher.matches();
        }
        return true;
    }


}
