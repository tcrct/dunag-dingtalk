package com.duangframework.dingtalk.sdk.dto;

public interface IDingtalk<T> {

    void setResult(T resultObj);

    T getResult();
}
