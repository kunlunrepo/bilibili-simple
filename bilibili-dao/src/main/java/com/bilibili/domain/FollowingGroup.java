package com.bilibili.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * description : 关注组
 *
 * @author kunlunrepo
 * date :  2024-01-15 16:14
 */
@Data
public class FollowingGroup {

    private Long id;

    private Long userId;

    private String name;

    private String type;

    private Date createTime;

    private Date updateTime;

    private List<UserInfo> followingUserInfoList;

}
