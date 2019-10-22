package org.subbs.controller;

import java.util.Map;

/**
 * 用作结果集发给前端
 * Created with IntelliJ IDEA.
 * User: peng
 * Date: 10/22/19
 * Time: 8:49 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Result {
    int success;
    String msg;
    Map data;

    public Result(){}

    public Result(int success) {
        this.success = success;
    }

    public Result(int success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Result(int success, String msg, Map data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
