package com.chen.manager.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service层——基层
 * <p>
 * created at 2019-10-31
 *
 * @param <T>
 * @param <ID>
 * @author MonoWing
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 保存单个实体
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 保存实体列表
     *
     * @param entities
     * @return
     */
    Iterable<T> saveAll(Iterable<T> entities);

    /**
     * 通过实体ID删除
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 通过实体删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 通过实体列表删除
     *
     * @param entities
     */
    void deleteAll(Iterable<T> entities);

    /**
     * 判断ID是否存在
     *
     * @param id
     * @return
     */
    boolean exists(ID id);

    /**
     * 通过ID获取实体
     *
     * @param id
     * @return
     */
    Optional<T> get(ID id);

    /**
     * 获取所有的实体
     *
     * @return
     */
    Iterable<T> findAll();

    /**
     * 通过ID列表获取实体
     *
     * @param ids
     * @return
     */
    Iterable<T> findAll(Iterable<ID> ids);

    /**
     * 通过分页获取实体
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

}
