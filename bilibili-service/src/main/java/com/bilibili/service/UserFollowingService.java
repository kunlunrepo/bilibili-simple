package com.bilibili.service;

import com.bilibili.dao.UserFollowingDao;
import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.User;
import com.bilibili.domain.UserFollowing;
import com.bilibili.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-15 10:54
 */
@Service
public class UserFollowingService {


    @Autowired
    FollowingGroupService followingGroupService;

    @Autowired
    UserService userService;

    @Autowired
    UserFollowingDao userFollowingDao;

    // 新增用户关注
    @Transactional
    public void addUserFollowings(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        // 查询分组
        if (groupId == null) {
            // 默认关注分组类型
            FollowingGroup followingGroup = followingGroupService.getByType("2");
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if (followingGroup == null) {
                throw new RuntimeException("关注分组不存在");
            }
        }
        // 查询关注用户
        Long followerId = userFollowing.getFollowingId();
        User user = userService.getUserByUserId(followerId);
        if (user == null) {
            throw new RuntimeException("关注用户不存在");
        }
        // 删除原有关注
        userFollowingDao.deleteUserFollowing(userFollowing.getUserId(), followerId);
        userFollowing.setCreateTime(new Date());
        // 新增关注
        userFollowingDao.addUserFollowing(userFollowing);
    }

    // 查询用户关注
    public List<FollowingGroup> getUserFollowingByUserId(Long userId) {
        // 根据用户编号查询指定用户的关注用户集合
        List<UserFollowing> userFollowings = userFollowingDao.getUserFollowingsByUserId(userId);
        // 获取关注用户编号集合
        List<Long> followingIds = userFollowings.stream().map(UserFollowing::getFollowingId)
                .collect(
                        Collectors.toList()
                );
        // 查询关注用户的用户信息集合
        List<UserInfo> userInfoList = new ArrayList<>();
        if (followingIds.size() > 0) {
            userInfoList = userService.getUserInfoByUserIds(followingIds);
        }
        // 拼接指定用户的关注用户的用户信息
        for (UserFollowing userFollowing : userFollowings) {
            for (UserInfo userInfo : userInfoList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())) {
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }
        // 根据用户编号查询关注分组
        List<FollowingGroup> followingGroupList = followingGroupService.getFollowingGroupsByUserId(userId);
        // 新增全部关注分组
        FollowingGroup allFollowingGroup = new FollowingGroup();
        allFollowingGroup.setName("全部关注");
        allFollowingGroup.setFollowingUserInfoList(userInfoList);

        // 返回的关注分组
        List<FollowingGroup> result = new ArrayList<>();
        result.add(allFollowingGroup);
        // 遍历关注分组
        for (FollowingGroup followingGroup : followingGroupList) {
            // 关注分组下用户集合
            List<UserInfo> followingUserInfoList = new ArrayList<>();
            // 遍历关注用户集合
            for (UserFollowing userFollowing : userFollowings) {
                if (followingGroup.getId().equals(userFollowing.getGroupId())) {
                    followingUserInfoList.add(userFollowing.getUserInfo());
                }
            }
            // 关注分组添加关注用户
            followingGroup.setFollowingUserInfoList(followingUserInfoList);
            // 添加返回结果
            result.add(followingGroup);
        }
        return result;
    }

    // 查询指定用户是谁的粉丝(未理解)
    public List<UserFollowing> getUserFansByUserId(Long userId) {
        // 根据用户编号查询指定用户的粉丝用户集合
        List<UserFollowing> fanList = userFollowingDao.getUserFansByFollowingId(userId);
        // 获取粉丝用户编号集合
        List<Long> fanIds = fanList.stream().map(UserFollowing::getUserId)
                .collect(
                        Collectors.toList()
                );
        // 查询粉丝用户的用户信息集合
        List<UserInfo> userInfoList = new ArrayList<>();
        if (fanIds.size() > 0) {
            userInfoList = userService.getUserInfoByUserIds(fanIds);
        }
        // 根据用户编号查询用户关注集合
        List<UserFollowing> userFollowingList = userFollowingDao.getUserFollowingsByUserId(userId);
        // 遍历粉丝集合
        for (UserFollowing fan : fanList) {
            // 组装粉丝用户信息
            for (UserInfo userInfo : userInfoList) {
                if (fan.getUserId().equals(userInfo.getUserId())) {
                    fan.setUserInfo(userInfo);
                    // 重置粉丝的关注状态
                    userInfo.setFollowed(false);
                }
            }
            // 确认粉丝的关注状态
            for (UserFollowing userFollowing : userFollowingList) {
                if (fan.getUserId().equals(userFollowing.getFollowingId())) {
                    // 确认粉丝的关注状态
                    fan.getUserInfo().setFollowed(true);
                }
            }
        }
        return fanList;
    }
}
