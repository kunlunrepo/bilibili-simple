package com.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * description : 用户信息
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:02
 */
@Data
public class UserInfo {

    private Long id;

    private Long userId;

    private String nick;

    // 头像
    private String avatar;

    // 个性签名
    private String sign;

    private String gender;

    private String birth;

    private Date createTime;

    private Date updateTime;

    // 是否关注
    private Boolean followed;



}
