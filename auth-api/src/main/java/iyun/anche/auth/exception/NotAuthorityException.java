package iyun.anche.auth.exception;

/**
 *  没有权限
 **/
public class NotAuthorityException extends RuntimeException{

    public NotAuthorityException() {
        this("没有权限！");
    }

    public NotAuthorityException(String message) {
        super(message);
    }
}
