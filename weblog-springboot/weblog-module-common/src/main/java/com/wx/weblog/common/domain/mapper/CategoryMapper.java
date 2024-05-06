package com.wx.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wx.weblog.common.domain.dos.CategoryDO;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 2024/4/18 10:17
 * @Describe:
 */
public interface CategoryMapper extends BaseMapper<CategoryDO> {
    /**
     * 根据分类名称查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);

        return selectOne(wrapper);
    }

    /**
     * 查询分类分页数据
     * @param current
     * @param size
     * @param name
     * @param startDate
     * @param endDate
     * @return
     */
    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNoneBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);

        return selectPage(page, wrapper);
    }
}
