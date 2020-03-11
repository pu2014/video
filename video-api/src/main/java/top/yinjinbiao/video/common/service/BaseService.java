package top.yinjinbiao.video.common.service;

import java.util.List;

public interface BaseService<T> {

    public int save(T t);

    public T getOne(T t);

    public int update(T t);

    public int count(T t);

    public List<T> page(T t);

}
