package com.chen.manager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
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

	@Query(value = "select * from sys_admin where if(?1 !='', user_name like ?1,1=1) and if(?2 !='', name like ?2,1=1)", nativeQuery = true)
	Page<Admin> pageRole(String userName, String name, PageRequest pageRequest);

}
