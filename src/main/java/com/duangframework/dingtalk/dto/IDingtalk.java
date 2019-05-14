package com.duangframework.dingtalk.dto;

public interface IDingtalk<T> {

    void setResult(T resultObj);

    T getResult();
}
