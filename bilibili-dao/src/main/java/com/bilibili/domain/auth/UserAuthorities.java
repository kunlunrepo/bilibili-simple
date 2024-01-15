package com.bilibili.domain.auth;

import lombok.Data;

import java.util.List;

/**
 * description : 用户权限
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:17
 */
@Data
public class UserAuthorities {

    private List<AuthRoleElementOperation> roleElementOperations;

    private List<AuthRoleMenu> roleMenuList;

}
