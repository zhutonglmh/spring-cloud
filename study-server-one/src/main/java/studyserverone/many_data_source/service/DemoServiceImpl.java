package studyserverone.many_data_source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studyserverone.easy_poi.dao.ScmYearEndMapper;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.many_data_source.DataSourceContextHolder;

import javax.annotation.Resource;

/**
 * @author zhutong
 * @date 2019/8/7 15:26
 */
@Service
public class DemoServiceImpl implements DemoService{
    
    @Autowired
    private ScmYearEndMapper scmYearEndMapper;

    @Autowired
    private studyserverone.easy_poi.dao2.ScmYearEndMapper scmYearEndMapper2;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ScmYearEnd scmYearEnd) {
    
        scmYearEndMapper.insert(scmYearEnd);
        scmYearEndMapper2.insert(scmYearEnd);
//        throw new RuntimeException();
    }
}
