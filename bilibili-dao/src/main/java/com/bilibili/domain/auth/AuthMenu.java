package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description : 菜单
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:26
 */
@Data
public class AuthMenu {

    private Long id;

    private String name;

    private String code;

    private Date createTime;

    private Date updateTime;
}
