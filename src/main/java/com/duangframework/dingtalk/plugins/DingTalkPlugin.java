package com.duangframework.dingtalk.plugins;

import com.duangframework.dingtalk.utils.DangtalkUtils;
import com.duangframework.dingtalk.utils.DingTalkConfig;
import com.duangframework.mvc.plugin.IPlugin;

/**
 * 钉钉插件类
 * @author laotang
 * @date 2019-05-14
 */
public class DingTalkPlugin implements IPlugin {


    public DingTalkPlugin() {
        DangtalkUtils.setDingTalkConfig(new DingTalkConfig());
    }
    public DingTalkPlugin(DingTalkConfig config) {
        DangtalkUtils.setDingTalkConfig(config);
    }

    @Override
    public void start() throws Exception {
        DingTalkConfig config = DangtalkUtils.getDingtalkConfig();
        if(null == config) {
            throw new NullPointerException("config is null");
        }
    }

    @Override
    public void stop() throws Exception {
        DangtalkUtils.setDingTalkConfig(null);
    }
}
