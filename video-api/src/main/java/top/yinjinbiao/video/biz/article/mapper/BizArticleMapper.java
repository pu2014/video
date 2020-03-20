package top.yinjinbiao.video.biz.article.mapper;

import top.yinjinbiao.video.domain.BizArticle;

import java.util.List;

public interface BizArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizArticle record);

    int insertSelective(BizArticle record);

    BizArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizArticle record);

    int updateByPrimaryKey(BizArticle record);

    List<BizArticle> listSelective(BizArticle record);
}