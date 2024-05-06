package com.wx.weblog.web.model.vo.article;

import com.wx.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: wx
 * @Date: 2024/4/26 10:11
 * @Describe:
 */
@Data
@Builder
@ApiModel(value = "首页查询文章分页 VO")
public class FindIndexArticlePageListReqVO extends BasePageQuery {
}
