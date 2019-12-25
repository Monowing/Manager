package com.chen.manager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chen.manager.entity.Role;

/**
 * DAO层——角色
 * 
 * created at 2019-12-2
 * 
 * @author MonoWing
 *
 */
@Repository
public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

	/**
	 * 通过角色名查询角色分页数据
	 * 
	 * @param name
	 *            角色名
	 * @param pageRequest
	 *            分页条件
	 * @return
	 */
	@Query(value = "select * from sys_role where if(?1 !='', name like ?1,1=1)", nativeQuery = true)
	Page<Role> pageRole(String name, PageRequest pageRequest);

}
