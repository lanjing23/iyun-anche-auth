package iyun.anche.auth.common.enumeration;


/**
 * auth 失败信息转化
 */
public enum AuthExceptionEnum {
    BAD_REQUEST("Bad Request","身份信息校验不通过"),
    CLIENT_UNAUTHORIZED("Unauthorized", "终端授权校验不通过"),
    AUTHORIZED_FAIL("Full authentication is required to access this resource", "授权认证校验失败"),
    INVALID_ACCESS_TOKEN("Invalid access token", "无效的token");
    ;

    private String raw;

    private String msg;

    AuthExceptionEnum(String raw, String msg) {
        this.raw = raw;
        this.msg = msg;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
