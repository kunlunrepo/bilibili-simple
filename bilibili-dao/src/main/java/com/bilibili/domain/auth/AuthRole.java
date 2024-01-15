package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:28
 */
@Data
public class AuthRole {

    private Long id;

    private String name;

    private String code;

    private Date createTime;

    private Date updateTime;

}
