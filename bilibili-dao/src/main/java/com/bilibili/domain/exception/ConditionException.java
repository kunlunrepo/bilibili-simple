package com.bilibili.domain.exception;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-12 14:58
 */
public class ConditionException extends RuntimeException{

    // 序列化ID
    private static final long serialVersionUID = 1L;

    private String code;

    // 构造函数
    public ConditionException(String code, String msg){
        super(msg);
        this.code = code;
    }

    public ConditionException(String name) {
        super(name);
        code = "500";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
