package com.wx.weblog.admin.service;

import com.wx.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/17 14:59
 * @Describe:
 */
public interface AdminUserService {
    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    Response findUserInfo();
}
