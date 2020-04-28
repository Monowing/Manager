package com.chen.manager.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chen.manager.entity.SysLog;

/**
 * DAO层——系统日志
 * <p>
 * created at 2019-12-24
 *
 * @author MonoWing
 */
@Repository
public interface SysLogDao extends PagingAndSortingRepository<SysLog, Long> {


}
