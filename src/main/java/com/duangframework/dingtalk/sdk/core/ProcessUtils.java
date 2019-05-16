package com.duangframework.dingtalk.sdk.core;

import com.aliyun.oss.ServiceException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.sdk.dto.ProcessDto;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.kit.ObjectKit;
import com.duangframework.mvc.http.enums.HttpMethod;
import java.util.Date;

/**
 * 审批
 * @author laotang
 * @date 2019-05-14
 */
public class ProcessUtils {

    private static final String CREATE_PROCESS_API = "https://oapi.dingtalk.com/topapi/processinstance/create";
    /**
     *   发起审批实例
     *   https://oapi.dingtalk.com/topapi/processinstance/create?access_token=ACCESS_TOKEN
     *
     */
    public static DingtalkResponse<OapiProcessinstanceCreateResponse> createApproval(ProcessDto processDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_PROCESS_API);
            OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
            ObjectKit.copyFields(processDto, request);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiProcessinstanceCreateResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("发起审批实例出错: " + e.getMessage(), e);
        }
    }

    private static final String BATCH_GET_APPROVAL_ID_API = "https://oapi.dingtalk.com/topapi/processinstance/listids";
    /**
     *   批量获取审批id
     *
     *   https://oapi.dingtalk.com/topapi/processinstance/listids?access_token=ACCESS_TOKEN
     *
     *  @param processCode 流程模板唯一标识，可在OA管理后台编辑审批表单部分查询
     *  @param startTime 开始时间。Unix时间戳
     *  @param endTime 结束时间，默认取当前时间。Unix时间戳
     *  @param size 分页参数，每页大小，最多传10，默认值：10
     *  @param cursor 分页查询的游标，最开始传0，后续传返回参数中的next_cursor值，默认值：0
     *  @param useridList 发起人用户id列表，用逗号分隔，最大列表长度：10
     */
    public static DingtalkResponse<OapiProcessinstanceListidsResponse> batchGetApprovalId(String processCode, Date startTime, Date endTime, long size, long cursor, String useridList) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(BATCH_GET_APPROVAL_ID_API);
            OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
            req.setProcessCode(processCode);
            req.setStartTime(startTime.getTime());
            req.setEndTime(endTime.getTime());
            req.setSize(size);
            req.setCursor(cursor);
            req.setUseridList(useridList);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiProcessinstanceListidsResponse response = client.execute(req, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("发起审批时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_SINGLE_APPROVAL_API = "https://oapi.dingtalk.com/topapi/processinstance/get";
    /**
     *   获取单个审批
     *
     *   https://oapi.dingtalk.com/topapi/processinstance/get?access_token=ACCESS_TOKEN
     *
     *  @param processInstanceId 审批实例id
     */
    public static DingtalkResponse<OapiProcessinstanceGetResponse> getSingleApproval(String processInstanceId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_SINGLE_APPROVAL_API);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(processInstanceId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiProcessinstanceGetResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取单个审批时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_USER_PENDING_APPROVAL_NUMBER_API = "https://oapi.dingtalk.com/topapi/process/gettodonum";
    /**
     *   获取用户待审批数量
     *
     *   https://oapi.dingtalk.com/topapi/process/gettodonum?access_token=ACCESS_TOKEN
     *
     *  @param userId 用户id
     */
    public static DingtalkResponse<OapiProcessGettodonumResponse> getUserPendingApprovalNumber(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_PENDING_APPROVAL_NUMBER_API);
            OapiProcessGettodonumRequest req = new OapiProcessGettodonumRequest();
            req.setUserid(userId);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiProcessGettodonumResponse rsp = client.execute(req, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            throw new ServiceException("获取单个审批时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_USER_VISIBLE_APPROVAL_API = "https://oapi.dingtalk.com/topapi/process/listbyuserid";
    /**
     *   获取用户可见的审批模板
     *
     *   https://oapi.dingtalk.com/topapi/process/listbyuserid?access_token=ACCESS_TOKEN
     *
     *  @param userId 用户id
     *  @param offset 分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     *  @param size 分页大小，最大可设置成100
     */
    public static DingtalkResponse<OapiProcessListbyuseridResponse> getUserVisibleApproval(String userId, long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_VISIBLE_APPROVAL_API);
            OapiProcessListbyuseridRequest request = new OapiProcessListbyuseridRequest();
            request.setUserid(userId);
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiProcessListbyuseridResponse rsp = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            throw new ServiceException("获取用户可见的审批时出错: " + e.getMessage(), e);
        }
    }
}
