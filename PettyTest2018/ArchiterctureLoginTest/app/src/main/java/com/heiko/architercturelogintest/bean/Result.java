package com.heiko.architercturelogintest.bean;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/2/24
 */

public class Result {
    private boolean success;
    private String message;

    public Result() {
        success = true;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
