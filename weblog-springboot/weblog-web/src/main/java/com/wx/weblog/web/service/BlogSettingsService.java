package com.wx.weblog.web.service;

import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:43
 * @Describe:
 */
public interface BlogSettingsService {
    /**
     * 获取博客设置信息
     *
     * @return
     */
    Response findDetail();
}
