package com.wx.weblog.web.service;

import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;

/**
 * @Author: wx
 * @Date: 2024/4/27 15:59
 * @Describe:
 */
public interface ArchiveService {
    /**
     * 获取文章归档分页数据
     *
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}
