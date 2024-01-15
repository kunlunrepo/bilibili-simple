package com.bilibili.service;

import com.bilibili.dao.AuthRoleDao;
import com.bilibili.domain.auth.*;
import com.bilibili.domain.constant.AuthRoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description : 用户权限
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:11
 */
@Service
public class UserAuthService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthRoleDao authRoleDao;

    // 根据用户编号查询用户权限
    public UserAuthorities getUserAuthorities(Long userId) {
        // 根据用户编号查询角色集合
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(userId);
        // 返回角色编号列表
        List<Long> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 根据角色编号查询元素操作
        List<AuthRoleElementOperation> authRoleElementOperationList = authRoleService.getRoleElementOperationByRoleIds(roleIds);
        // 根据角色编号查询菜单
        List<AuthRoleMenu> authRoleMenuList = authRoleService.getRoleMenuByRoleIds(roleIds);
        UserAuthorities userAuths = new UserAuthorities();
        userAuths.setRoleElementOperations(authRoleElementOperationList);
        userAuths.setRoleMenuList(authRoleMenuList);
        return userAuths;
    }

    // 新增用户默认角色
    public void addUserDefaultRole(Long userId) {
        UserRole userRole = new UserRole();
        // 查询默认角色
        AuthRole authRole = authRoleDao.getRoleByCode(AuthRoleConstant.ROLE_LV0);
        userRole.setUserId(userId);
        userRole.setRoleId(authRole.getId());
        userRoleService.addUserRole(userRole);
    }

}
