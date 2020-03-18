package top.yinjinbiao.video.upload.service;

import top.yinjinbiao.video.domain.SysFile;

public interface SysFileService {

    public int deleteByPrimaryKey(Long id);


    public int insert(SysFile record);


    public int insertSelective(SysFile record);


    public SysFile selectByPrimaryKey(Long id);


    public int updateByPrimaryKeySelective(SysFile record);


    public int updateByPrimaryKey(SysFile record);

}
