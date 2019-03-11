package com.union.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoWei
 * @describe 
 * @time 2017/12/26,10:32
*/
public class ValidatorCache {

    private final static List<BaseValidator> list = new ArrayList<BaseValidator>();

    public static void add(BaseValidator baseValidator) {
        list.add(baseValidator);
    }

    public static List get() {
        return list;
    }

    public static BaseValidator get(int i) {
        return list.get(i);
    }


}
