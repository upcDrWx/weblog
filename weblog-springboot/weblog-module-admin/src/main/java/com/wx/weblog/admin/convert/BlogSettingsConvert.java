package com.wx.weblog.admin.convert;

import com.wx.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.wx.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.wx.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: wx
 * @Date: 2024/4/23 14:42
 * @Describe:
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
