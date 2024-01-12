package com.bilibili.api.support;

import com.bilibili.domain.exception.ConditionException;
import com.bilibili.service.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * description : 用户上下文
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:20
 */
@Component
public class UserSupport {


    public Long getCurrentUserId() {

        // 从header中获取用户id
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户");
        }
        return userId;
    }

}
