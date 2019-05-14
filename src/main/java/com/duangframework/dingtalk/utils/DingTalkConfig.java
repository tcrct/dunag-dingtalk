package com.duangframework.dingtalk.utils;

import com.duangframework.kit.PropKit;

public class DingTalkConfig {

    public static final String APPKEY_FIELD = "appKey";

    private String appKey;
    private String appSecret;
    // 企业ID
    private String corpId;
    // 应用agentId
    private String agentId;
    // 回调地址
    private String callbackUrl;
    // 加解密需要用到的token字符串，企业可以随机填写
    private String token;

    public DingTalkConfig() {
        loadProp();
    }

    public DingTalkConfig(String appKey, String appSecret, String corpId, String agentId, String callbackUrl, String token) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.corpId = corpId;
        this.agentId = agentId;
        this.callbackUrl = callbackUrl;
        this.token = token;
    }

    private void loadProp() {
        appKey = PropKit.get("dingtalk.appKey");
        appSecret = PropKit.get("dingtalk.appSecret");
        corpId = PropKit.get("dingtalk.corpId");
        agentId = PropKit.get("dingtalk.agentId");
        callbackUrl = PropKit.get("dingtalk.callbackUrl");
        token = PropKit.get("dingtalk.token");
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
