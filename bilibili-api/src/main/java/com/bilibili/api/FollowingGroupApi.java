package com.bilibili.api;

import com.bilibili.api.support.UserSupport;
import com.bilibili.domain.FollowingGroup;
import com.bilibili.service.FollowingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description : 关注分组接口
 *
 * @author kunlunrepo
 * date :  2024-01-15 17:53
 */
@RestController
public class FollowingGroupApi {

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserSupport userSupport;


    // 新增关注分组

    @PostMapping("/following-groups")
    public Long addFollowingGroup(@RequestBody FollowingGroup followingGroup)
    {
        return followingGroupService.addFollowingGroup(followingGroup);
    }

    // 查询关注分组
    @GetMapping("/following-groups")
    public List<FollowingGroup> getFollowingGroups()
    {
        Long userId = userSupport.getCurrentUserId();
        return followingGroupService.getFollowingGroupsByUserId(userId);
    }

}
