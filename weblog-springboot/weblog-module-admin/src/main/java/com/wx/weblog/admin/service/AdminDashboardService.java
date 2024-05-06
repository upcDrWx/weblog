package com.wx.weblog.admin.service;

import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/29 14:24
 * @Describe:
 */
public interface AdminDashboardService {
    /**
     * 获取仪表盘基础统计信息
     *
     * @return
     */
    Response findDashboardStatistics();

    /**
     * 获取文章发布热点统计信息
     * @return
     */
    Response findDashboardPublishArticleStatistics();

    /**
     * 获取文章最近一周 PV 访问量统计信息
     * @return
     */
    Response findDashboardPVStatistics();
}
