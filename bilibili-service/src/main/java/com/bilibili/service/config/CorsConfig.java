package com.bilibili.service.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description : 解决跨域配置
 *
 * @author kunlunrepo
 * date :  2024-01-12 17:01
 */
@Configuration
public class CorsConfig implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // 判断请求头是否有origin
        String origin = httpRequest.getHeader("Origin");
        if (origin != null) {
            // 设置允许跨域的请求头
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, userId, token, ut");//表明服务器支持的所有头信息字段
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setContentType("application/json;charset=UTF-8");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 如果是OPTIONS请求，直接返回
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
