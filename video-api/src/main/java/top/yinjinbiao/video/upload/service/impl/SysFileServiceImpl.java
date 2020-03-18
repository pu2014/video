package top.yinjinbiao.video.upload.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yinjinbiao.video.domain.SysFile;
import top.yinjinbiao.video.upload.mapper.SysFileMapper;
import top.yinjinbiao.video.upload.service.SysFileService;

import javax.annotation.Resource;
@Service
@Transactional(readOnly = true)
public class SysFileServiceImpl implements SysFileService {

    @Resource
    private SysFileMapper sysFileMapper;

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return sysFileMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int insert(SysFile record) {
        return sysFileMapper.insert(record);
    }

    @Transactional(readOnly = false)
    public int insertSelective(SysFile record) {
        return sysFileMapper.insertSelective(record);
    }

    public SysFile selectByPrimaryKey(Long id) {
        return sysFileMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(SysFile record) {
        return sysFileMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKey(SysFile record) {
        return sysFileMapper.updateByPrimaryKey(record);
    }

}
