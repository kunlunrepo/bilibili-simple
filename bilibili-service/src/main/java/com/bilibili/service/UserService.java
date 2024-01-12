package com.bilibili.service;

import com.bilibili.dao.UserDao;
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

import java.util.Date;

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

}
