package com.chen.manager.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.manager.dao.SysLogDao;
import com.chen.manager.entity.SysLog;
import com.chen.manager.service.SysLogService;

/**
 * ServiceImpl层——系统日志
 * <p>
 * created at 2019-12-24
 *
 * @author MonoWing
 */

@Transactional
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements
        SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    public void setBaseDao(SysLogDao sysLogDao) {
        super.setBaseDao(sysLogDao);
    }

}
