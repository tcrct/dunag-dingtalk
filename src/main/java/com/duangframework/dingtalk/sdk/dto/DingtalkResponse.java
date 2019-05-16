package com.duangframework.dingtalk.sdk.dto;

import com.duangframework.kit.ToolsKit;

import java.util.Map;

/**
 * 返回结果
 * @param <T>
 */
public class DingtalkResponse<T> implements IDingtalk<T> {

    private long beginTime = System.currentTimeMillis();
    private String resultClassName;
    private T result;

    public DingtalkResponse() {

    }
    public DingtalkResponse(long beginTime) {
        this.beginTime = beginTime;
    }
    public DingtalkResponse(T result) {
        this.result = result;
    }

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public void setResult(T result) {
        this.result = result;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getResultClassName() {
        return result.getClass().getName();
    }

    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }

    public Map toMap() {
        return ToolsKit.isNotEmpty(result) ? ToolsKit.jsonParseObject(ToolsKit.toJsonString(result), Map.class) : null;
    }

}

