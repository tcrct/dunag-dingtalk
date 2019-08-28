package com.duangframework.dingtalk.sdk.core;

import com.duangframework.kit.ThreadPoolKit;
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
        // 须以线程方式进行处理
        ThreadPoolKit.execute(new Runnable() {
            @Override
            public void run() {
                DingtalkHolder.CALLBACK.reg();
            }
        });
    }
}
