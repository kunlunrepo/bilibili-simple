package com.bilibili.dao;

import com.alibaba.fastjson.JSONObject;
import com.bilibili.domain.User;
import com.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:12
 */
@Mapper
public interface UserDao {

    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long id);

    UserInfo getUserInfoByUserId(Long userId);

    User getUserByPhoneOrEmail(@Param("phone") String phone, @Param("email") String email);

    Integer updateUsers(User user);

    Integer updateUserInfos(UserInfo userInfo);

    Integer pageCountUserInfos(Map<String, Object> params);

    List<UserInfo> pageListUserInfos(JSONObject params);

    // 根据用户编号集合先查询用户基本信息
    List<UserInfo> getUserInfosByUserIds(List<Long> userIds);
}
