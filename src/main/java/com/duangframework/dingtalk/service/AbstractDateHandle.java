package com.duangframework.dingtalk.service;

import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.service.handle.IDateHandle;
import com.duangframework.kit.ObjectKit;
import com.duangframework.kit.PropKit;
import com.duangframework.kit.ToolsKit;

/**
 * 数据处理抽象类
 * @author laotang
 * @date 2019-05-14
 */
public abstract class AbstractDateHandle {

    private static  IDateHandle HANDLE;
    private DingtalkResponse dingtalkResponse;

    public AbstractDateHandle() {
        if(null == HANDLE) {
            String instanceClass = PropKit.get("date.handle");
            if(ToolsKit.isEmpty(instanceClass)) {
                instanceClass = "com.duangframework.dingtalk.service.handle.MongodbDataHandle";
            }
            HANDLE = ObjectKit.newInstance(instanceClass);
        }
    }

    /**
     * 处理数据
     * @param optFlag      操作方式(insert, update, delete)
     * @param tableName     表名
     * @param response      钉钉返回的结果集
     */
    public void handleData(String optFlag,String tableName, DingtalkResponse response) {
        if(ToolsKit.isEmpty(response)) {
            throw new NullPointerException("DingtalkResponse is null");
        }
        HANDLE.handle(optFlag,tableName,response);
    }
}
