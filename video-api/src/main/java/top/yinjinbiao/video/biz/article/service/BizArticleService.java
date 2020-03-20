package top.yinjinbiao.video.biz.article.service;

import com.github.pagehelper.PageInfo;
import top.yinjinbiao.video.domain.BizArticle;

public interface BizArticleService {

    public int deleteByPrimaryKey(Long id);


    public int insert(BizArticle record);


    public int insertSelective(BizArticle record);


    public BizArticle selectByPrimaryKey(Long id);


    public int updateByPrimaryKeySelective(BizArticle record);


    public int updateByPrimaryKey(BizArticle record);


    public PageInfo<BizArticle> pageSelective(int start, int count, BizArticle record);

}
