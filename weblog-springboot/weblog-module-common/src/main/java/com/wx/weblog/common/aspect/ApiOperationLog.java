package com.wx.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * @author wx
 * @Describe: API 文档注解接口
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API 功能描述
     */
    String description() default "";
}
