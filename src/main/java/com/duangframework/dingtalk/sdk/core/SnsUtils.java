package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.DingTalkUtils;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnsUtils {

    private static final Logger logger = LoggerFactory.getLogger(SnsUtils.class);

    private static final String GET_USERINFO_BYCODE_API = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
    /**
     *   服务端通过临时授权码获取授权用户的个人信息
     *
     *  通过临时授权码Code获取用户信息，临时授权码只能使用一次。
     *  https://ding-doc.dingtalk.com/doc#/serverapi3/vmzkak
     *
     * @param code 临时授权码
     */
    public static DingtalkResponse<OapiSnsGetuserinfoBycodeResponse> getUserInfoByCode(String code) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USERINFO_BYCODE_API);
            OapiSnsGetuserinfoBycodeRequest request = new OapiSnsGetuserinfoBycodeRequest();
            request.setTmpAuthCode(code);
            request.setHttpMethod(HttpMethod.POST.name());
            String appKey = DingTalkUtils.getDingtalkConfig().getLoginAppKey();
            String appSecret = DingTalkUtils.getDingtalkConfig().getLoginAppSecret();
            OapiSnsGetuserinfoBycodeResponse response = client.execute(request, appKey, appSecret);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取用户详情时出错: " + e.getMessage(), e);
            return null;
        }
    }
}
