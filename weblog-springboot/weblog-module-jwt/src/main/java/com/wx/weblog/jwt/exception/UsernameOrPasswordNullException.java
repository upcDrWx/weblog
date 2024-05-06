package com.wx.weblog.jwt.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: wx
 * @Date: 2024/4/12 21:13
 * @Describe:
 */
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
