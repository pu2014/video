package top.yinjinbiao.video.biz.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.biz.article.mapper.BizArticleMapper;
import top.yinjinbiao.video.biz.article.service.BizArticleService;
import top.yinjinbiao.video.domain.BizArticle;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BizArticleServiceImpl implements BizArticleService{

    @Resource
    private BizArticleMapper bizArticleMapper;

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return bizArticleMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int insert(BizArticle record) {
        return bizArticleMapper.insert(record);
    }

    @Transactional(readOnly = false)
    public int insertSelective(BizArticle record) {
        return bizArticleMapper.insertSelective(record);
    }


    public BizArticle selectByPrimaryKey(Long id) {
        return bizArticleMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(BizArticle record) {
        return bizArticleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKey(BizArticle record) {
        return bizArticleMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<BizArticle> pageSelective(int pageNumber, int pageSize, BizArticle record) {
        PageHelper.startPage(pageNumber, pageSize);
        List<BizArticle> list = bizArticleMapper.listSelective(record);
        return new PageInfo<BizArticle>(list);
    }

}
