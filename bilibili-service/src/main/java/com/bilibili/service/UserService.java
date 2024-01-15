package com.bilibili.service;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.dao.UserDao;
import com.bilibili.domain.PageResult;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import com.bilibili.domain.exception.ConditionException;
import com.bilibili.service.util.IdUtil;
import com.bilibili.service.util.MD5Util;
import com.bilibili.service.util.RSAUtil;
import com.bilibili.service.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:08
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserAuthService userAuthService;

    // 新增用户
    @Transactional
    public void addUser(User user)
    {
        // 校验手机号
        if(StringUtils.isEmpty(user.getPhone()))
        {
            throw new ConditionException("手机号不能为空！");
        }
        // 校验是否已注册
        User dnUser = userDao.getUserByPhone(user.getPhone());
        if(dnUser != null)
        {
            throw new ConditionException("手机号已注册！");
        }
        // 生成密码
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password); // 客户端传上来的密文
        } catch (Exception e) {
            throw new ConditionException("密码解密错误！");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        user.setId(IdUtil.generate());
        userDao.addUser(user);
        // 新增用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick("萌新");
        userInfo.setBirth("1999-10-1");
        userInfo.setGender("0");
        userInfo.setCreateTime(now);
        userDao.addUserInfo(userInfo);
        // 添加用户默认权限角色
        userAuthService.addUserDefaultRole(user.getId());
    }

    // 查询用户
    public User getUserInfo(Long userId)
    {
        User user = userDao.getUserById(userId);
        UserInfo userInfo = userDao.getUserInfoByUserId(userId);
        user.setUserInfo(userInfo);
        return user;
    }

    // 获取用户token
    public String login(User user) throws Exception
    {
        // 校验email和phone
        if(StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhone()))
        {
            throw new ConditionException("参数异常！");
        }
        // 根据email和phone查询用户信息
        User dnUser = userDao.getUserByPhoneOrEmail(user.getPhone(),user.getEmail());
        if(dnUser == null)
        {
            throw new ConditionException("当前用户不存在！");
        }
        // 传入密码进行解密
        String resPassword;
        try {
            resPassword = RSAUtil.decrypt(user.getPassword());
        } catch (Exception e) {
            throw new ConditionException("密码解析错误！");
        }
        // md5加密
        String md5Password = MD5Util.sign(resPassword, dnUser.getSalt(), "UTF-8");
        if(!md5Password.equals(dnUser.getPassword()))
        {
            throw new ConditionException("密码错误！");
        }
        // 生成token
        return TokenUtil.generateToken(dnUser.getId());
    }

    // 修改用户
    public void updateUsers(User user) {
        if(user == null)
        {
            throw new ConditionException("参数异常！");
        }
        User dnUser = userDao.getUserById(user.getId());
        if(dnUser == null)
        {
            throw new ConditionException("当前用户不存在！");
        }
        // 更新用户密码
        if(StringUtils.isNotEmpty(user.getPassword()))
        {
            String resPassword;
            try {
                resPassword = RSAUtil.decrypt(user.getPassword());
                String md5Password = MD5Util.sign(resPassword, dnUser.getSalt(), "UTF-8");
                user.setPassword(md5Password);
            } catch (Exception e) {
                throw new ConditionException("密码解析错误！");
            }
        }
        // 更新时间
        user.setUpdateTime(new Date());
        userDao.updateUsers(user);
    }

    // 修改用户基本信息
    public void updateUserInfos(UserInfo userInfo) {
        if(userInfo == null)
        {
            throw new ConditionException("参数异常！");
        }
        User dnUser = userDao.getUserById(userInfo.getUserId());
        if(dnUser == null)
        {
            throw new ConditionException("当前用户不存在！");
        }
        // 更新用户基本信息
        userInfo.setUpdateTime(new Date());
        userDao.updateUserInfos(userInfo);
    }

    // 分页查询用户列表
    public PageResult<UserInfo> pageListUserInfos(JSONObject params) {
        Integer no = params.getInteger("no");
        Integer size = params.getInteger("size");
        if(no == null || size == null)
        {
            throw new ConditionException("参数异常！");
        }
        params.put("start", (no - 1) * size);
        params.put("limit", size);
        // 查询总数
        Integer total = userDao.pageCountUserInfos(params);
        List<UserInfo> list = new ArrayList<>();
        // 查询列表
        if(total > 0)
        {
            list = userDao.pageListUserInfos(params);
        }
        return new PageResult<>(total, list);
    }


    // 根据用户编号查询用户信息
    public User getUserByUserId(Long userId) {
        User user = userDao.getUserById(userId);
        if(user == null)
        {
            throw new ConditionException("当前用户不存在！");
        }
        return user;
    }

    // 根据用户编号集合查询用户基本信息集合
    public List<UserInfo> getUserInfoByUserIds(List<Long> userIds) {
        List<UserInfo> list = new ArrayList<>();
        if(userIds == null || userIds.size() == 0)
        {
            throw new ConditionException("参数异常！");
        }
        list = userDao.getUserInfosByUserIds(userIds);
        return list;
    }


}
