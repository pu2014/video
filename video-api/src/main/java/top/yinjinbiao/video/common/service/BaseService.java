package top.yinjinbiao.video.common.service;

import com.github.pagehelper.PageInfo;
import top.yinjinbiao.video.common.domain.BaseDomain;

public interface BaseService<T extends BaseDomain> {

    public int save(T t);

    public T getOne(T t);

    public int update(T t);

    public int count(T t);

    public PageInfo<T> page(int pageNum, int pageSize, T t);

}
