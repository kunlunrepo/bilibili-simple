package com.bilibili.api;

import com.bilibili.domain.auth.AuthRole;
import com.bilibili.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description : 角色接口
 *
 * @author kunlunrepo
 * date :  2024-01-15 14:30
 */
@RestController
public class AuthRoleApi {

    @Autowired
    private AuthRoleService authRoleService;

    // 新增角色
    @PostMapping("/add-role")
    public String addAuthRole(@RequestBody AuthRole authRole) {
        return authRoleService.addAuthRole(authRole);
    }
}
