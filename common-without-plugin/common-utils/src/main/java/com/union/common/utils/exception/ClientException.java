package com.union.common.utils.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 客户端异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientException extends RuntimeException{
    private Integer code;
    private String msg;
}
