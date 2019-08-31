package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;

import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.exception.ServiceException;
import com.duangframework.mvc.http.enums.HttpMethod;

/**
 * 通讯录权限范围
 * @author laotang
 * @date 2019-05-15
 */
public class AddressBookPermissionScopeUtils {

    private static final String GET_ADDRESS_BOOK_PERMISSION_SCOPE_API = "https://oapi.dingtalk.com/auth/scopes";
    /**
     *   获取通讯录权限范围
     *
     *   https://oapi.dingtalk.com/auth/scopes?access_token=ACCESS_TOKEN
     *
     */
    public static DingtalkResponse<OapiAuthScopesResponse> getExternalContactTagList() {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ADDRESS_BOOK_PERMISSION_SCOPE_API);
            OapiAuthScopesRequest request = new OapiAuthScopesRequest();
            request.setHttpMethod(HttpMethod.GET.name());
            OapiAuthScopesResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取通讯录权限范围时出错: " + e.getMessage(), e);
        }
    }

}
