package iyun.anche.auth.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈响应实体〉
 *
 * @author liusx
 * @since 1.0.0
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 8410365189054089657L;

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
