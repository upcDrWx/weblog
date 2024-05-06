package com.wx.weblog.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: wx
 * @Date: 2024/4/2 15:12
 * @Describe: 业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();

    }
}
