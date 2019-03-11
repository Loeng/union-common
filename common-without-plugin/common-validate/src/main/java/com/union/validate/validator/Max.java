package com.union.validate.validator;


import com.union.validate.BindingValidator;
import com.union.validate.annotation.Check;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @author GaoWei
 * @describe
 * @time 2017/12/26,10:26
*/
public class Max extends BindingValidator {

    @Override
    public boolean business(Check check, String fieldName, Object value) {
        if (StringUtils.isEmpty(check.max())) {
            return true;
        }
        BigDecimal cond = BigDecimal.valueOf(Long.valueOf(check.max()));
        BigDecimal param = BigDecimal.valueOf(Long.valueOf(value.toString()));
        if (param.compareTo(cond) <= 0) {
            return true;
        }
        this.msg = "#name#不能大于" + cond;
        return false;
    }


}
