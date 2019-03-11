package com.union.validate.validator;



import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:32
*/
public class Url extends BindingValidator {

    private String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (check.url()) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(value));
            this.msg = "#name#必须为Url格式" ;
            return matcher.matches();
        }
        return true;
    }


}
