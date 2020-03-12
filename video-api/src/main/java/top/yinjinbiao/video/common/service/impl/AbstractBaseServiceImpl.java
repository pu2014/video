package top.yinjinbiao.video.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.common.mapper.MyMapper;
import top.yinjinbiao.video.common.domain.BaseDomain;
import top.yinjinbiao.video.common.service.BaseService;

@Transactional(readOnly = true)
public abstract class AbstractBaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int save(T t) {
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t) {
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T getOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);

        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
        return pageInfo;
    }


}
