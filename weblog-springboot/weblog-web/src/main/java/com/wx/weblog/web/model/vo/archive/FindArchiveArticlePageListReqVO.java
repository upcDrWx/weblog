package com.wx.weblog.web.model.vo.archive;

import com.wx.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: wx
 * @Date: 2024/4/27 15:54
 * @Describe:
 */
@Data
@Builder
@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
