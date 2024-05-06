package com.wx.weblog.admin.service;

import com.wx.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.wx.weblog.admin.model.vo.tag.AddTagReqVO;
import com.wx.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.wx.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.wx.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.wx.weblog.common.utils.PageResponse;
import com.wx.weblog.common.utils.Response;

/**
 * @Author: wx
 * @Date: 2024/4/19 10:11
 * @Describe:
 */
public interface AdminTagService {

    /**
     * 添加标签集合
     * @param addTagReqVO
     * @return
     */
    Response addTags(AddTagReqVO addTagReqVO);

    /**
     * 查询标签分页
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * 根据标签关键词模糊查询
     * @return
     */
    Response searchTag(SearchTagReqVO searchTagReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();
}
