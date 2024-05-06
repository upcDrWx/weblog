package com.wx.weblog.web.convert;

import com.wx.weblog.common.domain.dos.BlogSettingsDO;
import com.wx.weblog.web.model.vo.blogsettings.FindBlogSettingsDetailRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:39
 * @Describe:
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);


    /**
     * 将 DO 转化为 VO
     *
     * @param bean
     * @return
     */
    FindBlogSettingsDetailRspVO convertDO2VO(BlogSettingsDO bean);

}
