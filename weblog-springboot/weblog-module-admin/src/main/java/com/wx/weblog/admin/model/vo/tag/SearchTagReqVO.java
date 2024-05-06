package com.wx.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: wx
 * @Date: 2024/4/19 10:13
 * @Describe: 标签
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签模糊查询")
public class SearchTagReqVO {

    @NotBlank(message = "标签查询关键词不能为空")
    private String key;
}
