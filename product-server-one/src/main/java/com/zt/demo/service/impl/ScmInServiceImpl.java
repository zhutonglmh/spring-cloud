package com.zt.demo.service.impl;

import com.zt.demo.service.ScmInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhutong
 * @date 2018/12/21 16:59
 */
@Service
public class ScmInServiceImpl implements ScmInService {
    
    @Resource
    private ScmInMapper scmInMapper;
    /**
     * 根据条件进行查询
     *
     * @param scmIn 入参
     * @return 返回结果
     */
    @Override
    public ScmIn queryById(ScmIn scmIn) {
        return scmInMapper.selectByPrimaryKey(scmIn.getId());
    }
}
