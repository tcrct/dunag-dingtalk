package com.duangframework.dingtalk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.dingtalk.utils.DingTalkConfig;
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
    /**注册回调事件*/
    private static final List<String> CALLBACK_EVENT_LIST = new ArrayList<String>(){{
        this.add("user_add_org");
        this.add("user_modify_org");
        this.add("bpms_instance_change");
        this.add("bpms_task_change");
    }} ;

    /**
     * 删除企业回调接口url
     */
    public static final String DELETE_CALLBACK = "https://oapi.dingtalk.com/call_back/delete_call_back";
    /**
     * 注册企业回调接口url
     */
    public static final String REGISTER_CALLBACK = "https://oapi.dingtalk.com/call_back/register_call_back";

    public static void reg() {
        /**
         * 先删除企业已有的回调
         */
        DingTalkClient client = new DefaultDingTalkClient(DELETE_CALLBACK);
        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
        request.setHttpMethod(HttpMethod.GET.name());
        try {
            client.execute(request, AuthUtils.getAccessToken());
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
        registerRequest.setCallBackTag(CALLBACK_EVENT_LIST);
        try {
            OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest, AuthUtils.getAccessToken());
            if (registerResponse.isSuccess()) {
                System.err.println("回调注册成功了！！！");
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(),e);
        }
    }

}
