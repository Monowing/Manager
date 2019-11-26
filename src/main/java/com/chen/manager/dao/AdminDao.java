package com.chen.manager.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chen.manager.entity.Admin;

/**
 * DAO层——管理员
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 */
@Repository
public interface AdminDao extends PagingAndSortingRepository<Admin, Long> {

	List<Admin> findByUserName(String userName);

}
