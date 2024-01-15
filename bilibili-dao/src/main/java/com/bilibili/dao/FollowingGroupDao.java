package com.bilibili.dao;

import com.bilibili.domain.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 16:33
 */
@Mapper
public interface FollowingGroupDao {

    // 根据类型查询一个关注分组
    FollowingGroup getFollowingGroupByType(String type);

    // 根据编号查询一个关注分组
    FollowingGroup getFollowingGroupById(Long id);

    // 根据用户编号查询分组信息
    List<FollowingGroup> getFollowingGroupsByUserId(Long userId);

    // 新增关注分组
    Integer addFollowingGroup(FollowingGroup followingGroup);

}
