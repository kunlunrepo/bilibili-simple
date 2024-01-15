package com.bilibili.api;

import com.bilibili.api.support.UserSupport;
import com.bilibili.domain.FollowingGroup;
import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.UserFollowing;
import com.bilibili.service.UserFollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description : 用户关注接口
 *
 * @author kunlunrepo
 * date :  2024-01-15 15:32
 */
@RestController
public class UserFollowingApi {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserFollowingService userFollowingService;

    // 新增用户关注
    @PostMapping("/user-followings")
    public JsonResponse<String> addFollowings(@RequestBody UserFollowing userFollowing)
    {
        Long userId = userSupport.getCurrentUserId();
        userFollowing.setUserId(userId);
        userFollowingService.addUserFollowings(userFollowing);
        return JsonResponse.success();
    }

    // 查询用户关注
    @GetMapping("/user-followings")
    public JsonResponse<List<FollowingGroup>> getUserFollowings()
    {
        Long userId = userSupport.getCurrentUserId();
        return JsonResponse.success(userFollowingService.getUserFollowingByUserId(userId));
    }

    // 查询用户粉丝
    @GetMapping("/user-fans")
    public JsonResponse<List<UserFollowing>> getUserFans()
    {
        Long userId = userSupport.getCurrentUserId();
        return JsonResponse.success(userFollowingService.getUserFansByUserId(userId));
    }
}
