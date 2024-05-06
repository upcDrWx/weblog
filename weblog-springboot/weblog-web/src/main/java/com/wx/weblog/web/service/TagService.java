package com.wx.weblog.web.service;

import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:28
 * @Describe:
 */
public interface TagService {
    /**
     * 获取标签列表
     *
     * @return
     */
    Response findTagList();

    /**
     * 获取标签下文章分页列表
     *
     * @param findTagArticlePageListReqVO
     * @return
     */
    Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
