package com.wx.weblog.admin.service;

import com.wx.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/23 13:52
 * @Describe:
 */
public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客设置详情
     * @return
     */
    Response findDetail();
}
