package com.bilibili.api;

import com.bilibili.api.support.UserSupport;
import com.bilibili.domain.JsonResponse;
import com.bilibili.domain.User;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception
    {
        // 获取token
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

}
