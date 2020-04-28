package com.chen.manager.serviceimpl;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.chen.manager.service.BaseService;

/**
 * ServiceImpl层——基层实现层
 * <p>
 * created at 2019-10-31
 *
 * @param <T>
 * @param <ID>
 * @author MonoWing
 */
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements
        BaseService<T, ID> {

    private PagingAndSortingRepository<T, ID> baseRepository;

    public void setBaseDao(PagingAndSortingRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return baseRepository.saveAll(entities);
    }

    @Override
    public void delete(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<T> entities) {
        baseRepository.deleteAll(entities);
    }

    @Override
    public boolean exists(ID id) {
        return baseRepository.existsById(id);
    }

    @Override
    public Optional<T> get(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return baseRepository.findAllById(ids);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * 模糊查询处理：将字符串拼接上%
     *
     * @param str 字符串
     * @return
     */
    public String fieldLike(String str) {
        return "%" + str + "%";
    }

}