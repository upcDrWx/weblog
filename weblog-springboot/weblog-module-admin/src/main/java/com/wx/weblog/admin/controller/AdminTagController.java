package com.wx.weblog.admin.controller;

import com.wx.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.wx.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.wx.weblog.admin.model.vo.tag.AddTagReqVO;
import com.wx.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.wx.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.wx.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.wx.weblog.admin.service.AdminCategoryService;
import com.wx.weblog.admin.service.AdminTagService;
import com.wx.weblog.common.aspect.ApiOperationLog;
import com.wx.weblog.common.utils.PageResponse;
import com.wx.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wx
 * @Date: 2024/4/19 10:09
 * @Describe: 标签
 */
@RestController
@RequestMapping("/admin/tag")
@Api(tags = "Admin 标签模块")
public class AdminTagController {
    @Autowired
    private AdminTagService adminTagService;

    @PostMapping("/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    public Response addTags(@RequestBody @Validated AddTagReqVO addTagReqVO)
    {
        return adminTagService.addTags(addTagReqVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "标签分页数据获取")
    @ApiOperationLog(description = "标签分页数据获取")
    public PageResponse findTagPageList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return adminTagService.findTagPageList(findTagPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除标签")
    @ApiOperationLog(description = "删除标签")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return adminTagService.deleteTag(deleteTagReqVO);
    }

    @PostMapping("/search")
    @ApiOperation(value = "标签模糊查询")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTag(@RequestBody @Validated SearchTagReqVO searchTagReqVO) {
        return adminTagService.searchTag(searchTagReqVO);
    }

    @PostMapping("/select/list")
    @ApiOperation(value = "查询标签 Select 列表数据")
    @ApiOperationLog(description = "查询标签 Select 列表数据")
    public Response findTagSelectList() {
        return adminTagService.findTagSelectList();
    }
}

