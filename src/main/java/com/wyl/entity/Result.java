package com.wyl.entity;

import com.wyl.constants.HttpCode;
import lombok.Data;

@Data
public class Result {
    private static final String ERROR_CODE = "-1";
    private static final String SUCCESS_MSG = "请求成功";
    private static final String ERROR_MSG = "请求失败";


    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(HttpCode.OK);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(ERROR_MSG);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
