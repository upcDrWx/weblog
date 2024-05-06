package com.wx.weblog.admin.service;

import com.wx.weblog.common.utils.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wx
 * @Date: 2024/4/23 13:08
 * @Describe:
 */
public interface AdminFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Response uploadFile(MultipartFile file);
}
