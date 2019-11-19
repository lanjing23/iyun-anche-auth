package iyun.anche.auth.common.utils;


import iyun.anche.auth.common.enumeration.ResultCodeEnum;
import iyun.anche.auth.common.vo.Result;

/**
 * 返回值统一格式处理
 */
public class ResultUtil {

    public static Result success() {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAILED.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result fail(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAILED.getCode());
        result.setMsg(ResultCodeEnum.FAILED.getMsg());
        result.setData(data);
        return result;
    }
}
