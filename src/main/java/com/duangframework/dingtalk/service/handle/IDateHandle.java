package com.duangframework.dingtalk.service.handle;

import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;

public interface IDateHandle<T> {

    /**
     * 处理器
     * @param flag      操作方式(insert, update, delete)
     * @param tableName     表名
     * @param response      钉钉返回的结果集
     */
    void handle(String flag, String tableName, DingtalkResponse<T> response);
}
