package com.wx.weblog.common.utils;

import com.wx.weblog.common.enums.ResponseCodeEnum;
import com.wx.weblog.common.exception.BaseExceptionInterface;
import com.wx.weblog.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wx
 * @Date: 2024/4/2 11:21
 * @Describe:
 */
@Data
public class Response<T> implements Serializable {
    // 是否成功, 默认为 true
    private boolean success = true;
    // 响应消息
    private String message;
    // 异常码
    private String errorCode;
    // 响应数据
    private T data;

    // =================================== 成功响应 ===================================
    public static <T> Response<T> success() {
        return new Response<>();
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    // =================================== 失败响应 ===================================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(String message, String errorCode) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(bizException.getErrorMessage());
        response.setErrorCode(bizException.getErrorCode());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(baseExceptionInterface.getErrorMessage());
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        return response;
    }
}
