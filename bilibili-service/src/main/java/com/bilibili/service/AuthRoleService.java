package com.bilibili.service;

import com.bilibili.dao.AuthRoleDao;
import com.bilibili.domain.auth.AuthRole;
import com.bilibili.domain.auth.AuthRoleElementOperation;
import com.bilibili.domain.auth.AuthRoleMenu;
import com.bilibili.service.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * description : 角色
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:32
 */
@Service
public class AuthRoleService {

    @Autowired
    private AuthRoleElementOperationService authRoleElementOperationService;

    @Autowired
    private AuthRoleMenuService authRoleMenuService;

    @Autowired
    private AuthRoleDao authRoleDao;


    // 根据角色编号集合查询角色元素操作
    public List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(List<Long> roleIds) {

        return authRoleElementOperationService.getRoleElementOperationsByRoleIds(roleIds);
    }

    // 根据角色编号集合查询角色菜单
    public List<AuthRoleMenu> getRoleMenuByRoleIds(List<Long> roleIds) {

        return authRoleMenuService.getRoleMenusByRoleIds(roleIds);
    }

    // 新增角色
    public String addAuthRole(AuthRole authRole) {
        if (authRole == null) {
            return "参数异常";
        }
        AuthRole addAuthRole = new AuthRole();
        addAuthRole.setId(IdUtil.generate());
        addAuthRole.setName(authRole.getName());
        addAuthRole.setCode(authRole.getCode());
        addAuthRole.setCreateTime(new Date());
        authRoleDao.addRole(addAuthRole);
        return "新增角色成功";
    }
}
