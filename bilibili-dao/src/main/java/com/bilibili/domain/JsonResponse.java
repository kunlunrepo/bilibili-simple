package com.bilibili.domain;

/**
 * description : 统一返回结果
 *
 * @author kunlunrepo
 * date :  2024-01-12 13:54
 */
public class JsonResponse<T> {

    private String code;

    private String msg;

    private T data;

    // 构造方法
    public JsonResponse(T data) {
        this.data=data;
        this.code="0";
        this.msg="成功";
    }

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 静态方法成功
    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse<T>(data);
    }

    // 静态方法失败
    public static JsonResponse<String> fail() {
        return new JsonResponse<>("1", "失败");
    }

    public static <T> JsonResponse<T> fail(String code, String msg) {
        return new JsonResponse<T>(code, msg);
    }

    // get set
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
