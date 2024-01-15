package com.bilibili.dao;

import com.bilibili.domain.UserFollowing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description : 用户关注
 *
 * @author kunlunrepo
 * date :  2024-01-15 16:17
 */
public interface UserFollowingDao {

    // 根据用户编号和关注用户编号删除
    Integer deleteUserFollowing(@Param("userId") Long userId, @Param("followingId") Long followingId);

    // 新增用户关注
    Integer addUserFollowing(UserFollowing userFollowing);

    // 根据用户编号查询关注用户
    List<UserFollowing> getUserFollowingsByUserId(Long userId);

    // 根据用户编号查询粉丝
    List<UserFollowing> getUserFansByFollowingId(Long followingId);
}
