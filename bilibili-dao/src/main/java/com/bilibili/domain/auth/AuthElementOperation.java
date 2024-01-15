package com.bilibili.domain.auth;

import lombok.Data;

import java.util.Date;

/**
 * description : 元素操作
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:21
 */
@Data
public class AuthElementOperation {

    private Long id;

    private Long elementName;

    private Long elementCode;

    private Long operationType;

    private Date createTime;

    private Date updateTime;
}
