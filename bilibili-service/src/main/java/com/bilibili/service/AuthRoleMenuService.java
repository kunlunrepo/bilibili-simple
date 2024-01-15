package com.bilibili.service;

import com.bilibili.dao.AuthRoleMenuDao;
import com.bilibili.domain.auth.AuthRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description : 菜单
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:57
 */
@Service
public class AuthRoleMenuService {

    @Autowired
    private AuthRoleMenuDao authRoleMenuDao;

    // 根据角色编号集合获取角色菜单
    public List<AuthRoleMenu> getRoleMenusByRoleIds(List<Long> roleIds) {
        return authRoleMenuDao.getRoleMenusByRoleIds(roleIds);
    }
}
