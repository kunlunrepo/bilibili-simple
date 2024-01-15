package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description : 用户角色
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:17
 */
@Data
public class UserRole {

    private Long id;

    private Long userId;

    private Long roleId;

    private String roleName;

    private String roleCode;

    private Date createTime;
}
