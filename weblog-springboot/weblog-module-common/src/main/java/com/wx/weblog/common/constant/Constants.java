package com.wx.weblog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * @Author: wx
 * @Date: 2024/4/29 15:40
 * @Describe:
 */
public interface Constants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
}
