package com.bilibili.dao;

import com.bilibili.domain.UserMoment;
import org.apache.ibatis.annotations.Mapper;

/**
 * description : 用户动态dao
 *
 * @author kunlunrepo
 * date :  2024-01-16 09:22
 */
@Mapper
public interface UserMomentsDao {

    Integer addUserMoments(UserMoment userMoment);

}
