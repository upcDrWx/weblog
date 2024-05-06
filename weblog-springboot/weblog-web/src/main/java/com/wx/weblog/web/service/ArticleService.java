package com.wx.weblog.web.service;

import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.model.vo.article.FindArticleDetailReqVO;
import com.wx.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;

/**
 * @Author: wx
 * @Date: 2024/4/26 10:15
 * @Describe:
 */
public interface ArticleService {
    /**
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    /**
     * 获取文章详情
     *
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
}
