package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description : 角色和元素操作关系
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:19
 */
@Data
public class AuthRoleElementOperation {

    private Long id;

    private Long roleId;

    private Long elementOperationId;

    private Date createTime;

    private AuthElementOperation authElementOperation;
}
