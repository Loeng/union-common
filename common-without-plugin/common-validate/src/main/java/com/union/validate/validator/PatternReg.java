package com.union.validate.validator;


import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:32
*/
public class PatternReg extends BindingValidator {

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if(!StringUtils.isEmpty(check.pattern())){
            Pattern pattern = Pattern.compile(check.pattern());
            Matcher matcher = pattern.matcher(String.valueOf(value));
            return matcher.matches();
        }
        return true;
    }
}
