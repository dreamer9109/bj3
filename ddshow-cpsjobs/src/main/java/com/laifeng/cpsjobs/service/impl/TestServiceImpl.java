package com.laifeng.cpsjobs.service.impl;

import com.laifeng.cpsjobs.dao.stat.TestMapper;
import com.laifeng.cpsjobs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhengcunwen on 2015/6/26.
 */
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public int getListCount() {
        return testMapper.getListCount();
    }
}
