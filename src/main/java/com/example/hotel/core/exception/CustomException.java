package com.example.hotel.core.exception;

public class CustomException extends RuntimeException{
    private Integer errorCode;
    private Integer httpStatusCode;

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }


    public CustomException(String message, Throwable cause, Integer errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Integer httpStatusCode, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    public CustomException(String messageKey) {
        super(messageKey);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
