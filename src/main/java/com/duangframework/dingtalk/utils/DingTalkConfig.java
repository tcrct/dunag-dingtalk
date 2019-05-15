package com.duangframework.dingtalk.utils;

import com.duangframework.kit.Prop;
import com.duangframework.kit.PropKit;

public class DingTalkConfig {

    public static final String APPKEY_FIELD = "appKey";

    private String appKey;
    private String appSecret;
    /**  企业ID*/
    private String corpId;
    /** 应用agentId*/
    private String agentId;
    /** 回调地址*/
    private String callbackUrl;
    /** 加解密需要用到的token字符串，企业可以随机填写*/
    private String token;
    /** 加密关键字*/
    private String encodingAesKey;

    public DingTalkConfig() {
        loadProp();
    }

    public DingTalkConfig(Builder builder) {
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.corpId = builder.corpId;
        this.agentId = builder.agentId;
        this.callbackUrl = builder.callbackUrl;
        this.token = builder.token;
    }

    public DingTalkConfig(String appKey, String appSecret, String corpId, String agentId, String callbackUrl, String token,String encodingAesKey) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.corpId = corpId;
        this.agentId = agentId;
        this.callbackUrl = callbackUrl;
        this.token = token;
        this.encodingAesKey = encodingAesKey;
    }

    private void loadProp() {
        Prop prop = PropKit.use("dingtalk.properties");
        appKey = prop.get("dingtalk.appKey");
        appSecret = prop.get("dingtalk.appSecret");
        corpId = prop.get("dingtalk.corpId");
        agentId = prop.get("dingtalk.agentId");
        callbackUrl = prop.get("dingtalk.callbackUrl");
        token = prop.get("dingtalk.token");
        encodingAesKey = prop.get("dingtalk.encodingAesKey");
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

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public static class Builder {
        private String appKey;
        private String appSecret;
        /**  企业ID*/
        private String corpId;
        /** 应用agentId*/
        private String agentId;
        /** 回调地址*/
        private String callbackUrl;
        /** 加解密需要用到的token字符串，企业可以随机填写*/
        private String token;
        private String encodingAesKey;

        public Builder appKey(String appKey) {
            this.appKey = appKey;
            return this;
        }
        public Builder appSecret(String appSecret) {
            this.appSecret = appSecret;
            return this;
        }
        public Builder corpId(String corpId) {
            this.corpId = corpId;
            return this;
        }
        public Builder agentId(String agentId) {
            this.agentId = agentId;
            return this;
        }
        public Builder callbackUrl(String callbackUrl) {
            this.callbackUrl = callbackUrl;
            return this;
        }
        public Builder token(String token) {
            this.token = token;
            return this;
        }
        public Builder encodingAesKey(String encodingAesKey) {
            this.encodingAesKey = encodingAesKey;
            return this;
        }
        public DingTalkConfig build(){
            return new DingTalkConfig(this);
        }
    }

}
