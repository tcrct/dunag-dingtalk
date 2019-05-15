package com.duangframework.dingtalk;

import com.duangframework.dingtalk.core.SystemRun;
import com.duangframework.dingtalk.plugins.DingTalkPlugin;
import com.duangframework.dingtalk.utils.DingTalkConfig;
import com.duangframework.mvc.http.enums.EnvEnum;
import com.duangframework.mvc.plugin.IPlugin;
import com.duangframework.mvc.plugin.PluginChain;
import com.duangframework.server.Application;
import java.util.List;

public class Duang {

    public static void main(String[] args) {
        Application.duang().port(9393)
                .plugins(new PluginChain() {
                    @Override
                    public void addPlugin(List<IPlugin> pluginList) throws Exception {
                       pluginList.add(new DingTalkPlugin(new DingTalkConfig.Builder()
                               .appKey("dinggjkrqywanvuwqyg4")
                               .appSecret("FGyB63klKK0qwB6QZZWHFWi__x5w5KZpDX8YKtDcgLTWb9wKdtL7MjUYIQ1MpVOV")
                               .corpId("dingdf8af56f5b1ad87935c2f4657eb6378f")
                               .agentId("259917663")
                               .callbackUrl("https://dev.signetz.com/dingtalk/callback")
                               .token("5c691d2553837344133787d95c691d25538373441337")
                                .build()));
                        }
                })
                .env(EnvEnum.DEV)
//                .init(new SystemRun())
                .run();
    }
}
