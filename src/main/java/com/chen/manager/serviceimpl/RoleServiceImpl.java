package com.chen.manager.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.manager.dao.RoleDao;
import com.chen.manager.entity.Role;
import com.chen.manager.service.RoleService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * ServiceImpl层——角色
 * <p>
 * created at 2019-12-02
 *
 * @author MonoWing
 */

@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements
        RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    public void setBaseDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
    }

    @Override
    public Page<Role> pageRole(String keyword, PageRequest pageRequest) {
        return roleDao.pageRole(fieldLike(keyword), pageRequest);
    }

    @Override
    public Role insertRole(Role role) {
        role.setInternalSign(false);
        return save(role);
    }

    @Override
    public Role editRole(Role role) {
        Long id = role.getId();
        Role original = get(id).get();

        BeanUtils.copyProperties(original, role, "name", "description");

        return save(role);
    }

    @Override
    public CommonResult deleteRole(List<Long> ids) {
        CommonResult result = new CommonResult();

        if (ids == null || ids.size() <= 0) {
            return result.error("数据为空，无法删除！");
        }

        for (Long id : ids) {
            if (id == null || id <= 0) {
                continue;
            }
            Role role = get(id).get();
            if (role == null || role.getInternalSign() == null) {
                continue;
            }

            if (role.getInternalSign()) {
                return result.error("数据包含系统角色，不可删除！");
            }

        }

        for (Long id : ids) {
            delete(id);
        }

        return result.success("删除成功");
    }

}
