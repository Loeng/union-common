package com.union.tx.lcn;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


/**
*@author GaoWei
*descrption: 事务管理器bean
*time  2018/4/1 14:58
*/
@CommonsLog
@Configuration
public class TxManagerTxUrlServiceBean implements TxManagerTxUrlService {

    @Resource
    private DiscoveryClient discoveryClient;

    @Value(value = "${tx-manager}")
    private String txMangerUrl;

    @Override
    public String getTxUrl() {
        String tmUrl = "";
        if (txMangerUrl==null||"".equals(txMangerUrl)) {
            throw new NullPointerException("tm.manager.url 不能为空");
        }//单服务
        if (txMangerUrl.contains("http://")) {
            tmUrl = txMangerUrl;
        } else {
            //高可用
            List<ServiceInstance> list = discoveryClient.getInstances(txMangerUrl);
            tmUrl = loadBalance(list).getUri()+ "/tx/manager/";
        }

        return tmUrl;
    }

    /**
     * 负载的算法,随机获取一个tm 连接
     * @param list
     * @return
     */
    private ServiceInstance loadBalance(List<ServiceInstance> list) {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        return list.get(index);
    }

}
