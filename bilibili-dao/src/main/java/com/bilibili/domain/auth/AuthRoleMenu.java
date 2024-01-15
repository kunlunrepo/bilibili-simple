package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description : 角色和菜单关系
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:24
 */
@Data
public class AuthRoleMenu {

    private Long id;

    private Long roleId;

    private Long menuId;

    private Date createTime;

    private AuthMenu authMenu;
}
