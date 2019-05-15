package com.duangframework.dingtalk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.duangframework.mvc.core.InitRun;

/**
 * @author laotang
 * @date 2019-05-15
 */
public class SystemRun implements InitRun {
    @Override
    public void before() throws Exception {

    }

    @Override
    public void after() throws Exception {
        DingtalkHolder.CALLBACK.reg();
    }
}
