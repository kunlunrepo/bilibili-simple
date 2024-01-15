package com.bilibili.api;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.api.support.UserSupport;
import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.PageResult;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description : 用户接口
 *
 * @author kunlunrepo
 * date :  2024-01-12 13:50
 */
@RestController
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    private UserSupport userSupport;

    // 新增用户
    @PostMapping(value = "/users")
    public JsonResponse<String> addUser(@RequestBody User user)
    {
        // 新增用户
        userService.addUser(user);
        return JsonResponse.success();
    }

    // 查询用户
    @GetMapping(value = "/users")
    public JsonResponse<User> getUserInfo()
    {
        // 获取当前用户id
        Long userId = userSupport.getCurrentUserId();
        // 获取用户信息
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }

    // 用户登录
    @PostMapping(value = "/user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception
    {
        // 获取token
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

    // 修改用户
    @PutMapping("/users")
    public JsonResponse<String> updateUsers(@RequestBody User user)
    {
        Long userId = userSupport.getCurrentUserId();
        user.setId(userId);
        // 修改用户
        userService.updateUsers(user);
        return JsonResponse.success();
    }

    // 修改用户基本信息
    @PutMapping("/user-infos")
    public JsonResponse<String> updateUserInfo(@RequestBody UserInfo userInfo)
    {
        Long userId = userSupport.getCurrentUserId();
        userInfo.setUserId(userId);
        // 修改用户基本信息
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

    // 查询用户列表
    @GetMapping("/user-infos")
    public JsonResponse<PageResult<UserInfo>> pageListUserInfos(@RequestParam Integer no, @RequestParam Integer size, String nick)
    {
        // 获取当前用户id
        Long userId = userSupport.getCurrentUserId();
        // 查询参数
        JSONObject params = new JSONObject();
        params.put("no", no);
        params.put("size", size);
        if (nick != null && !nick.isEmpty())
        {
            params.put("nick", nick);
        }
        params.put("userId", userId);
        PageResult<UserInfo> result = userService.pageListUserInfos(params);
        if (result.getTotal() > 0) {
            // 查询用户粉丝
//            List<UserInfo> checkedUserInfoList = userFollowingService.checkFollowingStatus(result.getList(), userId);
//            result.setList(checkedUserInfoList);
        }
        return new JsonResponse<>(result);
    }
}
