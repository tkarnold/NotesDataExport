package com.example.notes.util.file;

/**
 * @author fanhongjiang
 * @version 1.0
 * @date 2019/11/15 9:23
 */
//前端通用对象
public class WebResult<T> {
    //状态 0成功 1失败
    private String status;
    //错误码
    private String code;
    //信息
    private String message;
    //数据总数
    private Long total;
    //具体数据
    private T data;

    public WebResult(String status, String code, String msg){
        this.code=code;
        this.message=msg;
        this.status=status;
    }
    public WebResult(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
