package cn.icatw.baseservice.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 统一异常处理
 *
 * @author 76218
 * @date 2022/09/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
 @ApiModelProperty(value = "状态码")
 private Integer code;
 private String msg;

}
