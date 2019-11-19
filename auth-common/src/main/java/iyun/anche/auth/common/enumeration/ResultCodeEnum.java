package iyun.anche.auth.common.enumeration;


/**
 * 〈响应状态码〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
public enum ResultCodeEnum {
    Exception(-1,"异常"),

    //处理成功
    SUCCESS(1, "成功"),

    //处理失败
    FAILED(0, "失败"),

    //未登录
    NOT_LOGIN(2, "not login"),

    //未激活
    NOT_ACTIVED(3, "account not actived"),

    //访问拒绝
    ACCESS_DENIED(4, "Access denied"),

    //数据库错误
    DB_ERROR(5, "Error querying database"),

    //参数错误
    PARAM_PARAMETER_ERROR(6, "Parameter error"),

    //参数为空
    PARAM_PARAMETER_IS_NULL(7, "Parameter is null"),
    ;

    private int code;

    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
