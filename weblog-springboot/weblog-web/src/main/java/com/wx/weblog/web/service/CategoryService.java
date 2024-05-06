package com.wx.weblog.web.service;

import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:22
 * @Describe:
 */
public interface CategoryService {
    /**
     * 获取分类列表
     *
     * @return
     */
    Response findCategoryList();

    /**
     * 获取分类下文章分页数据
     *
     * @param findCategoryArticlePageListReqVO
     * @return
     */
    Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}
