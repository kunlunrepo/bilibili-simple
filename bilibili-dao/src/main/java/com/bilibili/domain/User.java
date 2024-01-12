package com.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:00
 */
@Data
public class User {

    private Long id;

    private String phone;

    private String email;

    private String password;

    private String salt;

    private Date createTime;

    private Date updateTime;

    private UserInfo userInfo;
}
