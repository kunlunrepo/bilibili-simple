package com.bilibili.dao;

import com.bilibili.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description : 角色菜单
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:59
 */
@Mapper
public interface AuthRoleMenuDao {

    // 根据角色编号集合获取角色菜单集合
    List<AuthRoleMenu> getRoleMenusByRoleIds(List<Long> roleIds);
}
