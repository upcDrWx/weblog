package com.wx.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wx.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.wx.weblog.common.domain.dos.ArticleDO;
import com.wx.weblog.common.domain.dos.CategoryDO;
import com.wx.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.wx.weblog.common.domain.mapper.ArticleMapper;
import com.wx.weblog.common.domain.mapper.CategoryMapper;
import com.wx.weblog.common.enums.ResponseCodeEnum;
import com.wx.weblog.common.exception.BizException;
import com.wx.weblog.common.utils.PageResponse;
import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.convert.ArticleConvert;
import com.wx.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.wx.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.wx.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.wx.weblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: wx
 * @Date: 2024/4/26 15:22
 * @Describe:
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Response findCategoryList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<FindCategoryListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream().map(categoryDO -> FindCategoryListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }


        return Response.success(vos);
    }

    @Override
    public Response findCategoryArticlePageList(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {
        Long categoryId = findCategoryArticlePageListReqVO.getId();
        Long size = findCategoryArticlePageListReqVO.getSize();
        Long current = findCategoryArticlePageListReqVO.getCurrent();

        // 判断该分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.info("==> 该分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先查询该分类下所有关联的文章 ID
        List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectListByCategoryId(categoryId);

        if (CollectionUtils.isEmpty(articleCategoryRelDOS)) {
            log.info("==> 该分类下还未发布任何文章, categoryId: {}", categoryId);
            return PageResponse.success(null, null);
        }

        // 若该分类下未发布任何文章
        List<Long> articleIds = articleCategoryRelDOS.stream()
                .map(ArticleCategoryRelDO::getArticleId).collect(Collectors.toList());

        // 根据文章 ID 集合查询文章分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindCategoryArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream().map(articleDO -> ArticleConvert.INSTANCE.convertDO2CategoryArticleVO(articleDO))
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }
}
