package com.spring.template.exception;

/**
 * Created by umutbuyukdurmus on 6.09.2016.
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public CustomException(String errMsg) {
        this.errMsg = errMsg;
    }

}
