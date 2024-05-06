package com.wx.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wx.weblog.admin.event.ReadArticleEvent;
import com.wx.weblog.common.domain.dos.*;
import com.wx.weblog.common.domain.mapper.*;
import com.wx.weblog.common.enums.ResponseCodeEnum;
import com.wx.weblog.common.exception.BizException;
import com.wx.weblog.common.utils.PageResponse;
import com.wx.weblog.common.utils.Response;
import com.wx.weblog.web.convert.ArticleConvert;
import com.wx.weblog.web.markdown.MarkdownHelper;
import com.wx.weblog.web.model.vo.article.*;
import com.wx.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.wx.weblog.web.model.vo.tag.FindTagListRspVO;
import com.wx.weblog.web.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: wx
 * @Date: 2024/4/26 10:15
 * @Describe:
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 获取首页文章分页数据
     *
     * @param findIndexArticlePageListReqVO
     * @return
     */
    @Override
    public Response findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {
        Long current = findIndexArticlePageListReqVO.getCurrent();
        Long size = findIndexArticlePageListReqVO.getSize();

        // 第一步：分页查询文章主体记录
        Page<ArticleDO> articleDOPage = articleMapper
                .selectPageList(current, size, null, null, null);

        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindIndexArticlePageListRspVO> vos = null;

        if (!CollectionUtils.isEmpty(articleDOS)) {
            // 文章 DO 转 VO
            vos = articleDOS.stream().map(articleDO -> ArticleConvert.INSTANCE.convertDO2VO(articleDO))
                    .collect(Collectors.toList());

            List<Long> articleIds = articleDOS.stream().map(ArticleDO::getId).collect(Collectors.toList());

            // 第二步：设置文章所属分类
            // 查询所有分类
            List<CategoryDO> categoryDOS = categoryMapper.selectList(Wrappers.emptyWrapper());
            // 转 Map, 方便后续根据分类 ID 拿到对应的分类名称
            Map<Long, String> categoryIdNameMap = categoryDOS.stream()
                    .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));

            // 根据文章 ID 批量查询所有关联记录
            List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectByArticleIds(articleIds);

            vos.forEach(vo -> {
                Long currArticleId = vo.getId();

                // 过滤出当前文章对应的关联数据
                Optional<ArticleCategoryRelDO> optional = articleCategoryRelDOS.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), currArticleId)).findAny();

                if (optional.isPresent()) {
                    // 获取关联对象
                    ArticleCategoryRelDO articleCategoryRelDO = optional.get();
                    Long categoryId = articleCategoryRelDO.getCategoryId();
                    // 根据分类 id 从分类 Map 中得到 VO 对象，并设置到 vos 中
                    vo.setCategory(FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryIdNameMap.get(categoryId))
                            .build());
                }
            });

            // 第三步：设置文章标签
            // 查询所有标签
            List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());
            // 转 Map, 方便后续根据标签 ID 拿到对应的标签名称
            Map<Long, String> tagIdNameMap = tagDOS.stream()
                    .collect(Collectors.toMap(TagDO::getId, TagDO::getName));

            // 拿到所有文章的标签关联记录
            List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleIds(articleIds);

            vos.forEach(vo -> {
                Long currArticleId = vo.getId();
                // 过滤出当前文章的标签关联记录, 将关联记录 DO 转 VO, 并设置对应的标签名称
                List<FindTagListRspVO> findTagListRspVOS = articleTagRelDOS.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), currArticleId))
                        .map(rel -> FindTagListRspVO.builder()
                                .id(rel.getTagId())
                                .name(tagIdNameMap.get(rel.getTagId()))
                                .build())
                        .collect(Collectors.toList());

                vo.setTags(findTagListRspVOS);
            });
        }


        return PageResponse.success(articleDOPage, vos);
    }

    /**
     * 获取文章详情
     *
     * @param findArticleDetailReqVO
     * @return
     */
    @Override
    public Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO) {
        Long articleId = findArticleDetailReqVO.getArticleId();

        ArticleDO articleDO = articleMapper.selectById(articleId);

        // 判断文章是否存在
        if (Objects.isNull(articleDO)) {
            log.warn("==> 该文章不存在, articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_FOUND);
        }

        // 查询正文
        ArticleContentDO articleContentDO = articleContentMapper.selectByArticleId(articleId);

        // DO 转 VO
        FindArticleDetailRspVO vo = FindArticleDetailRspVO.builder()
                .title(articleDO.getTitle())
                .createTime(articleDO.getCreateTime())
                .content(MarkdownHelper.convertMarkdown2Html(articleContentDO.getContent()))
                .readNum(articleDO.getReadNum())
                .build();

        // 查询所属分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectByArticleId(articleId);
        CategoryDO categoryDO = categoryMapper.selectById(articleCategoryRelDO.getCategoryId());
        vo.setCategoryId(categoryDO.getId());
        vo.setCategoryName(categoryDO.getName());

        // 查询标签
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleId(articleId);
        List<Long> tagIds = articleTagRelDOS.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());
        List<TagDO> tagDOS = tagMapper.selectByIds(tagIds);

        // 标签 DO 转 VO
        List<FindTagListRspVO> tagVOS = tagDOS.stream()
                .map(tagDO -> FindTagListRspVO.builder().id(tagDO.getId()).name(tagDO.getName()).build())
                .collect(Collectors.toList());
        vo.setTags(tagVOS);


        // 上一篇
        ArticleDO preArticleDO = articleMapper.selectPreArticle(articleId);
        if (Objects.nonNull(preArticleDO)) {
            FindPreNextArticleRspVO preArticleVO = FindPreNextArticleRspVO.builder()
                    .articleId(preArticleDO.getId())
                    .articleTitle(preArticleDO.getTitle())
                    .build();
            vo.setPreArticle(preArticleVO);
        }

        // 下一篇
        ArticleDO nextArticleDO = articleMapper.selectNextArticle(articleId);
        if (Objects.nonNull(nextArticleDO)) {
            FindPreNextArticleRspVO nextArticleVO = FindPreNextArticleRspVO.builder()
                    .articleId(nextArticleDO.getId())
                    .articleTitle(nextArticleDO.getTitle())
                    .build();
            vo.setNextArticle(nextArticleVO);
        }

        // 发布文章阅读事件
        eventPublisher.publishEvent(new ReadArticleEvent(this, articleId));

        return Response.success(vo);
    }
}
