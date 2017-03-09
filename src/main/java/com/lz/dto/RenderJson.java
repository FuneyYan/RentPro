package com.lz.dto;

public class RenderJson {
    public static final String SUCCESS="success";
    public static final String ERROR="error";

    private String status;
    private Object data;
    private String message;

    /**
     * 请求正确
     * @param data 对象类型
     */
    public RenderJson(Object data){
        this.data=data;
        this.status=SUCCESS;
    }

    /**
     * 请求失败
     * @param status ERROR
     * @param message 错误信息
     */
    public RenderJson(String status,String message){
        this.status=status;
        this.message=message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
