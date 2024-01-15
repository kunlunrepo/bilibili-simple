package com.bilibili.dao;

import com.bilibili.domain.auth.AuthRoleElementOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 12:06
 */
@Mapper
public interface AuthRoleElementOperationDao {

    // 根据角色编号获取角色元素操作
    List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(List<Long> roleIds);

}
