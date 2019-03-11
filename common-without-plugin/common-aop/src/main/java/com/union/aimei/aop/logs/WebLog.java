package com.union.aimei.aop.logs;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * WebLog
 *
 * @author liufeihua
 * @date 2018/4/11 11:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@ApiModel(value = "mongo")
public class WebLog {

    @Id
    private String id;
    private String requestURL;
    private String requestURI;
    private String queryString;
    private String remoteAddr;
    private String remoteHost;
    private String remotePort;
    private String localAddr;
    private String localName;
    private String method;
    private String headers;
    private String parameters;
    private String classMethod;
    private String args;

    private String operateId;
    private String operateName;
    private String operateUserName;
    private Date createTime;
}
