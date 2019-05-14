package com.duangframework.dingtalk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.dto.ProcessDto;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.kit.ObjectKit;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author laotang
 * @date 2019-05-14
 */
public class ProcessUtils {

    private static final Logger logger = LoggerFactory.getLogger(ProcessUtils.class);

    private static final String CREATE_PROCESS_API = "https://oapi.dingtalk.com/topapi/processinstance/create";
    /**
     *   发起审批实例
     *   https://oapi.dingtalk.com/topapi/processinstance/create?access_token=ACCESS_TOKEN
     *
     */
    public static DingtalkResponse<OapiProcessinstanceCreateResponse> getDepartmentListIds(ProcessDto processDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_PROCESS_API);
            OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
            ObjectKit.copyFields(processDto, request);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiProcessinstanceCreateResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("发起审批实例出错: " + e.getMessage(), e);
            return null;
        }
    }
}
