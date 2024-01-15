package com.bilibili.service;

import com.bilibili.dao.UserRoleDao;
import com.bilibili.domain.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * description : 用户角色
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:32
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    // 新增用户角色
    public void addUserRole(UserRole userRole) {
        userRole.setCreateTime(new Date());
        userRoleDao.addUserRole(userRole);
    }

    // 查询用户角色列表
    public List<UserRole> getUserRoleByUserId(Long userId) {

        return userRoleDao.getUserRoleByUserId(userId);
    }
}
