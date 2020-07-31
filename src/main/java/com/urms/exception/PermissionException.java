package com.urms.exception;

/**
 * @author
 */
public class PermissionException extends RuntimeException{
    private Integer status;
    private String message;
    public static PermissionException NO_TOKEN_EXCEPTION = new PermissionException(400,"未发现Token 或者没有相应的权限");

    public PermissionException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public PermissionException() {
        super();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
