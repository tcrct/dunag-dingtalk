package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.response.OapiCallBackDeleteCallBackResponse;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.duangframework.dingtalk.service.strategy.EventTypeEnum;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.dingtalk.utils.DingTalkUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 回调注册工具类
 * @author laotang
 * @date 2019-05-15
 */
public class CallbackUtils {

    private static final Logger logger = LoggerFactory.getLogger(CallbackUtils.class);

    /**
     * 删除企业回调接口url
     */
    public static final String DELETE_CALLBACK = "https://oapi.dingtalk.com/call_back/delete_call_back";
    /**
     * 注册企业回调接口url
     */
    public static final String REGISTER_CALLBACK = "https://oapi.dingtalk.com/call_back/register_call_back";

    public static void reg() {

        /**注册回调事件*/
        List<String> callbackEventList = new ArrayList<>();
        for(EventTypeEnum typeEnum : EventTypeEnum.values()){
            callbackEventList.add(typeEnum.getType().trim());
        }
        String accessToken = "";
        /**
         * 先删除企业已有的回调
         */
        DingTalkClient client = new DefaultDingTalkClient(DELETE_CALLBACK);
        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
        request.setHttpMethod(HttpMethod.GET.name());
        try {
            accessToken = DingTalkAccessTokenUtils.getAccessToken();
            OapiCallBackDeleteCallBackResponse deleteCallBackResponse = client.execute(request, accessToken);
            if(deleteCallBackResponse.isSuccess()) {
                logger.warn("删除已有回调事件成功");
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }

        /**
         * 重新为企业注册回调
         */
        client = new DefaultDingTalkClient(REGISTER_CALLBACK);
        OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
        registerRequest.setUrl(DingTalkUtils.getDingtalkConfig().getCallbackUrl());
        registerRequest.setAesKey(DingTalkUtils.getDingtalkConfig().getEncodingAesKey());
        registerRequest.setToken(DingTalkUtils.getDingtalkConfig().getToken());
        /**
         * 需要注册的回调事件
         * 参考 https://open-doc.dingtalk.com/microapp/serverapi2/skn8ld
         */
        registerRequest.setCallBackTag(callbackEventList);
        try {
            registerRequest.setHttpMethod(HttpMethod.POST.name());
            OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest, accessToken);
            if (registerResponse.isSuccess()) {
                logger.warn("回调事件注册成功");
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(),e);
        }
    }

}
