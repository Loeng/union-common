package com.union.aimei.aop.logs;


/**
 * WebLogService
 *
 * @author liufeihua
 * @date 2018/4/11 11:28
 */
public interface WebLogService {

    void save(WebLog log);

    WebLogVo findList(Integer pageNo, Integer pageSize, WebLog record);
}
