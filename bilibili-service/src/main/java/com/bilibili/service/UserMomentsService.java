package com.bilibili.service;

import com.bilibili.dao.UserMomentsDao;
import com.bilibili.domain.UserMoment;
import com.bilibili.service.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * description : 用户动态
 *
 * @author kunlunrepo
 * date :  2024-01-16 09:20
 */
@Service
public class UserMomentsService {


    @Autowired
    private UserMomentsDao userMomentsDao;

    @Transactional
    public void addUserMoments(UserMoment userMoment) {
        // 新增用户动态
        userMoment.setId(IdUtil.generate());
        userMoment.setCreateTime(new Date());
        userMomentsDao.addUserMoments(userMoment);
        // 发布用户动态

    }

}
