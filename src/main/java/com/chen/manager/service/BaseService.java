package com.chen.manager.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service层——基层
 * 
 * created at 2019-10-31
 * 
 * @author MonoWing
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {
	
	T save(T entity);
	
	Iterable<T> saveAll(Iterable<T> entities);
	
	void delete(ID id);

	void delete(T entity);
	
	void deleteAll(Iterable<T> entities);

	boolean exists(ID id);

	Optional<T> get(ID id);
	
	Iterable<T> findAll();
	
	Iterable<T> findAll(Iterable<ID> ids);

	Page<T> findAll(Pageable pageable);

}
