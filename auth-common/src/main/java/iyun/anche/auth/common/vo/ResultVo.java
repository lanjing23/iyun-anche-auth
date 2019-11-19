package iyun.anche.auth.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回json格式
 * @param <T>
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 8410365189054089657L;

    private Integer code;

    private String msg;

    private T data;
}
