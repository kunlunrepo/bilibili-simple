package com.bilibili.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * description : MD5加密工具类
 *
 * @author kunlunrepo
 * date :  2024-01-12 15:44
 */
@Slf4j
public class MD5Util {

    // 签名
    public static String sign(String content, String salt, String charset) {
        // 返回16进制字符串(将原始2进制MD5摘要转换为16进制字符串形式返回)
        return DigestUtils.md5Hex(getContentBytes(content + salt, charset));
    }

    // 获取content的bytes
    public static byte[] getContentBytes(String content, String charset) {
        if (StringUtils.isNotEmpty(charset)) {
            // 使用指定的charset
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException e) {
                log.error("MD5签名，指定字符集错误", e.getMessage());
                throw new RuntimeException("MD5签名时, 使用指定字符集错误");
            }
        } else {
            // 默认使用utf-8
            return content.getBytes();
        }
    }
}
