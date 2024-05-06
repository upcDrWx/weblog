package com.wx.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wx.weblog.common.domain.dos.BlogSettingsDO;
import com.wx.weblog.common.domain.mapper.BlogSettingsMapper;
import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.convert.BlogSettingsConvert;
import com.wx.weblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import com.wx.weblog.web.service.BlogSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:44
 * @Describe:
 */
@Service
@Slf4j
public class BlogSettingsServiceImpl implements BlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    @Override
    public Response findDetail() {
        // 查询博客设置信息（约定的 ID 为 1）
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);

        // DO 转 VO
        FindBlogSettingsDetailRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(vo);
    }
}
