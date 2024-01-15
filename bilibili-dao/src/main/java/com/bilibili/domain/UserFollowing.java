package com.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * description : 用户关注
 *
 * @author kunlunrepo
 * date :  2024-01-15 15:36
 */
@Data
public class UserFollowing {

    private Long id;

    private Long userId;

    // 关注用户编号
    private Long followingId;

    // 关注分组编号
    private Long groupId;

    private Date createTime;

    private UserInfo userInfo;

}
