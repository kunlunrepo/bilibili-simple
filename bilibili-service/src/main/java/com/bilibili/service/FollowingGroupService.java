package com.bilibili.service;

import com.bilibili.dao.FollowingGroupDao;
import com.bilibili.domain.FollowingGroup;
import com.bilibili.service.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * description : 用户关注分组
 *
 * @author kunlunrepo
 * date :  2024-01-15 16:27
 */
@Service
public class FollowingGroupService {

    @Autowired
    private FollowingGroupDao followingGroupDao;

    // 根据类型查询一个分组信息
    public FollowingGroup getByType(String type) {
        return followingGroupDao.getFollowingGroupByType(type);
    }

    // 根据编号查询一个分组信息
    public FollowingGroup getById(Long id) {
        return followingGroupDao.getFollowingGroupById(id);
    }

    // 根据用户编号查询分组信息
    public List<FollowingGroup> getFollowingGroupsByUserId(Long userId) {
        return followingGroupDao.getFollowingGroupsByUserId(userId);
    }

    // 新增关注分组
    @Transactional
    public Long addFollowingGroup(FollowingGroup followingGroup) {
        followingGroup.setId(IdUtil.generate());
        followingGroup.setCreateTime(new Date());
        followingGroup.setType("3"); // 0特别关注  1悄悄关注 2默认分组  3用户自定义分组
        followingGroupDao.addFollowingGroup(followingGroup);
        return followingGroup.getId();
    }

}
