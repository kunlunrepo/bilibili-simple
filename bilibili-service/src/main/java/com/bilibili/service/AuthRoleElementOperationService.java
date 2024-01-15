package com.bilibili.service;

import com.bilibili.dao.AuthRoleElementOperationDao;
import com.bilibili.domain.auth.AuthRoleElementOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description : 元素操作
 *
 * @author kunlunrepo
 * date :  2024-01-15 11:55
 */
@Service
public class AuthRoleElementOperationService {


    @Autowired
    private AuthRoleElementOperationDao authRoleElementOperationDao;

    // 根据角色编号查询元素操作
    public List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(List<Long> roleIds){
        return authRoleElementOperationDao.getRoleElementOperationsByRoleIds(roleIds);
    }

}
