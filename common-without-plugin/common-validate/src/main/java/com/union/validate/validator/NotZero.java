package com.union.validate.validator;



import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:27
*/
public class NotZero extends BindingValidator {

    private String regex="^\\+?[1-9][0-9]*$";

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if(check.notZero()){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(String.valueOf(value));
            this.msg = "#name#不能为0" ;
            return matcher.matches();
        }
        return true;
    }

}
