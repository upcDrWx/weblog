package com.wx.weblog.admin.service;

import com.wx.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.wx.weblog.common.utils.PageResponse;
import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/18 10:26
 * @Describe:
 */
public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    // 省略..

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryPageList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);

    /**
     * 获取文章分类的 Select 列表数据
     * @return
     */
    Response findCategorySelectList();
}
