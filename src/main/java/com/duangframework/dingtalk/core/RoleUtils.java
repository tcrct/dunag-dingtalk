package com.duangframework.dingtalk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRoleListRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiRoleListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 角色管理
 * @author laotang
 * @date 2019-05-14
 */
public class RoleUtils {

    private static final Logger logger = LoggerFactory.getLogger(RoleUtils.class);

    private static final String GET_ROLE_LIST_API = "https://oapi.dingtalk.com/topapi/role/list";
    /**
     *   获取角色列表
     *
     *   https://oapi.dingtalk.com/topapi/role/list?access_token=ACCESS_TOKEN
     *
     * @param size  分页大小，默认值：20，最大值200
     * @param offset    分页偏移，默认值：0
     */
    public static DingtalkResponse<OapiRoleListResponse> getRoleList(long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ROLE_LIST_API);
            OapiRoleListRequest request = new OapiRoleListRequest();
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleListResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取角色列表时出错: " + e.getMessage(), e);
            return null;
        }
    }

}
