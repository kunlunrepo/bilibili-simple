package com.bilibili.domain;

import lombok.Data;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 10:20
 */
@Data
public class PageResult<T> {

    private Integer total;

    private List<T> list;

    // 全参构造函数
    public PageResult(Integer total, List<T> list) {
        this.total = total;
        this.list = list;
    }

}
