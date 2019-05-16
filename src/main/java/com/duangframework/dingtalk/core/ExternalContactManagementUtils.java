package com.duangframework.dingtalk.core;

import com.aliyun.oss.ServiceException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import java.util.List;

/**
 * 外部联系人管理
 * @author laotang
 * @date 2019-05-15
 */
public class ExternalContactManagementUtils {

    private static final String GET_EXTERNAL_CONTACT_TAG_LIST_API = "https://oapi.dingtalk.com/topapi/extcontact/listlabelgroups";
    /**
     *   获取外部联系人标签列表
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/listlabelgroups?access_token=ACCESS_TOKEN
     *
     *  @param offset 偏移位置
     *  @param size  分页大小，最大100
     */
    public static DingtalkResponse<OapiExtcontactListlabelgroupsResponse> getExternalContactTagList(long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_EXTERNAL_CONTACT_TAG_LIST_API);
            OapiExtcontactListlabelgroupsRequest request = new OapiExtcontactListlabelgroupsRequest();
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactListlabelgroupsResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取外部联系人标签列表时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_EXTERNAL_CONTACT_LIST_API = "https://oapi.dingtalk.com/topapi/extcontact/list";
    /**
     *   获取外部联系人列表
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/list?access_token=ACCESS_TOKEN
     *
     *   @param offset 偏移位置
     *   @param size  分页大小，最大100
     */
    public static DingtalkResponse<OapiExtcontactListResponse> getExternalContactList(long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_EXTERNAL_CONTACT_LIST_API);
            OapiExtcontactListRequest request = new OapiExtcontactListRequest();
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactListResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取外部联系人列表时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_ENTERPRISE_EXTERNAL_CONTACT_DETAILS_API = "https://oapi.dingtalk.com/topapi/extcontact/get";
    /**
     *   获取企业外部联系人详情
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/get?access_token=ACCESS_TOKEN
     *
     *   @param userId 用户id
     *
     */
    public static DingtalkResponse<OapiExtcontactGetResponse> getEnterpriseExternalContactDetails(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ENTERPRISE_EXTERNAL_CONTACT_DETAILS_API);
            OapiExtcontactGetRequest request = new OapiExtcontactGetRequest();
            request.setUserId(userId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactGetResponse response = client.execute(request,  AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取企业外部联系人详情时出错: " + e.getMessage(), e);
        }
    }

    private static final String CREATE_EXTERNAL_CONTACT_API = "https://oapi.dingtalk.com/topapi/extcontact/create";
    /**
     *   添加外部联系人
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/create?access_token=ACCESS_TOKEN
     *
     *   @param title 职位
     *   @param labelIds 标签列表
     *   @param shareDeptIds 共享给的部门ID
     *   @param address 地址
     *   @param remark 备注
     *   @param followerUserId 负责人userId
     *   @param name 名称
     *   @param stateCode 手机号国家码
     *   @param companyName 企业名
     *   @param shareUserIds 共享给的员工userId列表
     *   @param mobile 手机号
     */
    public static DingtalkResponse<OapiExtcontactCreateResponse > createExternalContact(String title, List<Long> labelIds, List<Long> shareDeptIds, String address,
            String remark, String followerUserId, String name, String stateCode, String companyName, List<String> shareUserIds, String mobile) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_EXTERNAL_CONTACT_API);
            OapiExtcontactCreateRequest request = new OapiExtcontactCreateRequest();
            OapiExtcontactCreateRequest.OpenExtContact openExtContact = new OapiExtcontactCreateRequest.OpenExtContact();
            openExtContact.setTitle(title);
            openExtContact.setLabelIds(labelIds);
            openExtContact.setShareDeptIds(shareDeptIds);
            openExtContact.setAddress(address);
            openExtContact.setRemark(remark);
            openExtContact.setFollowerUserId(followerUserId);
            openExtContact.setName(name);
            openExtContact.setStateCode(stateCode);
            openExtContact.setCompanyName(companyName);
            openExtContact.setShareUserIds(shareUserIds);
            openExtContact.setMobile(mobile);
            request.setContact(openExtContact);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactCreateResponse  response = client.execute(request,  AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("添加外部联系人时出错: " + e.getMessage(), e);
        }
    }

    private static final String UPDATE_EXTERNAL_CONTACT_API = "https://oapi.dingtalk.com/topapi/extcontact/update";
    /**
     *   更新外部联系人
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/update?access_token=ACCESS_TOKEN
     *
     *   @param userId 该外部联系人的userId
     *   @param title 职位
     *   @param labelIds 标签列表
     *   @param shareDeptIds 共享给的部门ID
     *   @param address 地址
     *   @param remark 备注
     *   @param followerUserId 负责人userId
     *   @param name 名称
     *   @param companyName 企业名
     *   @param shareUserIds 共享给的员工userId列表
     */
    public static DingtalkResponse<OapiExtcontactUpdateResponse> updateExternalContact(String userId, String title, List<Long> labelIds, List<Long> shareDeptIds,
             String address, String remark, String followerUserId, String name, String companyName, List<String> shareUserIds) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UPDATE_EXTERNAL_CONTACT_API);
            OapiExtcontactUpdateRequest request = new OapiExtcontactUpdateRequest();
            OapiExtcontactUpdateRequest.OpenExtContact openExtContact = new OapiExtcontactUpdateRequest.OpenExtContact();
            openExtContact.setUserId(userId);
            openExtContact.setTitle(title);
            openExtContact.setLabelIds(labelIds);
            openExtContact.setShareDeptIds(shareDeptIds);
            openExtContact.setAddress(address);
            openExtContact.setRemark(remark);
            openExtContact.setFollowerUserId(followerUserId);
            openExtContact.setName(name);
            openExtContact.setCompanyName(companyName);
            openExtContact.setShareUserIds(shareUserIds);
            request.setContact(openExtContact);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactUpdateResponse response = client.execute(request,  AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("更新外部联系人时出错: " + e.getMessage(), e);
        }
    }

    private static final String DELETE_EXTERNAL_CONTACT_API = "https://oapi.dingtalk.com/topapi/extcontact/delete";
    /**
     *   删除外部联系人
     *
     *   https://oapi.dingtalk.com/topapi/extcontact/delete?access_token=ACCESS_TOKEN
     *
     *   @param userId 用户id
     */
    public static DingtalkResponse<OapiExtcontactDeleteResponse> deleteExternalContact(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DELETE_EXTERNAL_CONTACT_API);
            OapiExtcontactDeleteRequest request = new OapiExtcontactDeleteRequest();
            request.setUserId(userId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiExtcontactDeleteResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("删除外部联系人时出错: " + e.getMessage(), e);
        }
    }
}
