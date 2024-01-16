package com.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * description : 用户动态
 *
 * @author kunlunrepo
 * date :  2024-01-16 09:18
 */
@Data
public class UserMoment {

    private Long id;

    private Long userId;

    private String type;

    private Long contentId;

    private Date createTime;

    private Date updateTime;
}
