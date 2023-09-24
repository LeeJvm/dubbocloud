package com.gavin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gavin.enums.ProcessCodeEnum;

import java.io.Serializable;

/**
 * Created by 17428 on 2023/7/30.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {


    private static final long serialVersionUID = -4308335504748232253L;

    /**
     * 将状态码和msq封装到enum
     */
    private ProcessCodeEnum enumDict;


    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;


    public ResponseResult() {
    }

    /**
     * 主要使用此方法作为返回result
     * @param enumDict
     * @param data
     */
    public ResponseResult(ProcessCodeEnum enumDict, T data) {
        this.enumDict = enumDict;
        this.data = data;
        this.code = enumDict.getCode();
        this.msg = enumDict.getMessage();
    }

    /**
     * 主要使用此方法作为返回result，如果没有数据返回的话
     * @param enumDict
     */
    public ResponseResult(ProcessCodeEnum enumDict) {
        this.enumDict = enumDict;
        this.code = enumDict.getCode();
        this.msg = enumDict.getMessage();
    }




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
