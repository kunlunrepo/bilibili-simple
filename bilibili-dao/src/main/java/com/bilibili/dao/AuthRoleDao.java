package com.bilibili.dao;

import com.bilibili.domain.auth.AuthRole;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 13:58
 */
@Mapper
public interface AuthRoleDao {

    // 根据角色code获取角色
    AuthRole getRoleByCode(String code);

    // 新增角色
    Integer addRole(AuthRole authRole);

}
