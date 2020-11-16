package com.haiyang.dynamic.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: Hai Yang
 * @Date: 2019/4/22 09:41
 * @Description:
 */
@Getter
@Setter
@ToString
public class ResponseWrapper<T> implements Serializable {

    private Boolean success;

    private Integer status;

    private String msg;

    private T data;

    public static final int STATUS_SUCCESS = 20000;

    public static final int STATUS_ERROR = 50000;
    public static final int STATUS_SYSTEM_ERROR = 50001;
    public static final int STATUS_NO_AUTH = 50100;
    public static final int STATUS_ISOLATION = 50200;
    public static final int SESSION_TIME_OUT = 50300;

    private ResponseWrapper(Boolean success, Integer status, String msg, T data) {

        this.success = success;

        this.status = status;

        this.msg = msg;

        this.data = data;
    }

    public static <E> ResponseWrapper<E> success() {

        return new ResponseWrapper<>(true, STATUS_SUCCESS, null, null);
    }

    public static <E> ResponseWrapper<E> success(String msg, E data) {

        return new ResponseWrapper<>(true, STATUS_SUCCESS, msg, data);
    }

    public static <E> ResponseWrapper<E> success(E data) {

        return new ResponseWrapper<>(true, STATUS_SUCCESS, null, data);
    }

    public static <E> ResponseWrapper<E> error() {

        return new ResponseWrapper<>(false, STATUS_ERROR, null, null);
    }

    public static <E> ResponseWrapper<E> error(String msg) {

        return new ResponseWrapper<>(false, STATUS_ERROR, msg, null);
    }

    public static <E> ResponseWrapper<E> error(String msg, E data) {

        return new ResponseWrapper<>(false, STATUS_ERROR, msg, data);
    }

    public static <E> ResponseWrapper<E> error(Integer status) {

        return new ResponseWrapper<>(false, status, null, null);
    }

    public static <E> ResponseWrapper<E> error(Integer status, String msg) {

        return new ResponseWrapper<>(false, status, msg, null);
    }

}
