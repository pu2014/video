package top.yinjinbiao.video.test.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yinjinbiao.video.test.mapper.TestMapper;
import top.yinjinbiao.video.test.domain.Test;

@Service
public class TestSerivce {

    @Autowired
    private TestMapper testMapper;

    public int save(Test test){
        return testMapper.insert(test);
    }

    public Test selectOne(Test test){
        return testMapper.selectOne(test);
    }
}
