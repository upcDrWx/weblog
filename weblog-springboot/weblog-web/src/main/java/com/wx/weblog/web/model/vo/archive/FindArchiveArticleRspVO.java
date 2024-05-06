package com.wx.weblog.web.model.vo.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @Author: wx
 * @Date: 2024/4/27 15:56
 * @Describe:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticleRspVO {
    private Long id;
    private String cover;
    private String title;
    /**
     * 发布日期
     */
    private LocalDate createDate;

    /**
     * 发布的月份（此字段不需要展示在前端，主要用于按月份分组使用）
     */
    private YearMonth createMonth;
}
