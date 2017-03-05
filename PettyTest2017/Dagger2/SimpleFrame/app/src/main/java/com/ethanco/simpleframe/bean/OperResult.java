package com.ethanco.simpleframe.bean;

/**
 * Created by EthanCo on 2016/4/4.
 */
public class OperResult<T> {

    public OperResult() {
    }

    public OperResult(Boolean isSuccess, String error, T entity) {
        this.isSuccess = isSuccess;
        this.error = error;
        this.entity = entity;
    }

    private Boolean isSuccess = false;
    private String error;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    private T entity;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
