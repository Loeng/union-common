package com.union.aimei.aop.logs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

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
@ApiModel(value = "mongo123")
public class WebLogVo {

    @ApiModelProperty("总数")
    private Long total;
    @ApiModelProperty("list")
    private List<WebLog> list;

}
