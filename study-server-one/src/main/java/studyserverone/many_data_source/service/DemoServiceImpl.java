package studyserverone.many_data_source.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import studyserverone.easy_poi.dao.ScmYearEndMapper;
import studyserverone.easy_poi.dao2.ScmYearEndMapper2;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.many_data_source.DataSourceType;
import studyserverone.many_data_source.DatabaseContextHolder;

import javax.annotation.Resource;

/**
 * @author zhutong
 * @date 2019/8/7 15:26
 */
@Service
public class DemoServiceImpl implements DemoService{
    
    @Resource
    private ScmYearEndMapper scmYearEndMapper;
    
    @Resource
    private ScmYearEndMapper2 scmYearEndMapper2;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void add(ScmYearEnd scmYearEnd) {
    
        DatabaseContextHolder.setDatabaseType(DataSourceType.DATA_SOURCE_ONE);

        scmYearEndMapper.insert(scmYearEnd);
        DatabaseContextHolder.setDatabaseType(DataSourceType.DATA_SOURCE_TWO);
        scmYearEndMapper2.insert(scmYearEnd);
    }
}
