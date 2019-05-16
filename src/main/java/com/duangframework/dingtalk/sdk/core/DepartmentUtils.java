package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.sdk.dto.DepartmentDto;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.kit.ObjectKit;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 部门管理
 * https://open-doc.dingtalk.com/microapp/serverapi2/dubakq
 * @author  laotang
 * @date 2019/5/9.
 */
public class DepartmentUtils {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentUtils.class);

    private static final String DEPARTMENT_IDS_API = "https://oapi.dingtalk.com/department/list_ids";
    /**
     *   获取子部门ID列表
     *   https://oapi.dingtalk.com/department/list_ids?access_token=ACCESS_TOKEN&id=ID
     *
     */
    public static DingtalkResponse<OapiDepartmentListIdsResponse> getDepartmentListIds(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DEPARTMENT_IDS_API);
            OapiDepartmentListIdsRequest request = new OapiDepartmentListIdsRequest();
            request.setId(departId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentListIdsResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取子部门ID列表出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String DEPARTMENT_API = "https://oapi.dingtalk.com/department/list";
    /**
     * 获取部门列表
     * https://oapi.dingtalk.com/department/list?access_token=ACCESS_TOKEN
     *
     * @parem departId  父部门id（如果不传，默认部门为根部门，根部门ID为1)
     */
    public static DingtalkResponse<OapiDepartmentListResponse> getDepartmentList(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DEPARTMENT_API);
            OapiDepartmentListRequest request = new OapiDepartmentListRequest();
            if(null == departId) {
                departId = "1";
            }
            request.setId(departId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentListResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门列表出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String DEPARTMENT_GET_API = "https://oapi.dingtalk.com/department/get";
    /**
     * 获取部门详情
    * https://oapi.dingtalk.com/department/get?access_token=ACCESS_TOKEN&id=123
     *
     * @parem departId  部门id
     */
    public static DingtalkResponse<OapiDepartmentGetResponse> getDepartment(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DEPARTMENT_GET_API);
            OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
            request.setId(departId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentGetResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门详情出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String DEPARTMENT_PARENT_API = "https://oapi.dingtalk.com/department/list_parent_depts_by_dept";
    /**
     * 查询部门的所有上级父部门路径
     * https://oapi.dingtalk.com/department/list_parent_depts_by_dept?access_token=ACCESS_TOKEN&id=ID
     *
     * @parem departId  希望查询的部门的id，包含查询的部门本身
     */
    public static DingtalkResponse<OapiDepartmentListParentDeptsByDeptResponse > getDepartmentListParentDepts(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DEPARTMENT_PARENT_API);
            OapiDepartmentListParentDeptsByDeptRequest request = new OapiDepartmentListParentDeptsByDeptRequest();
            request.setId(departId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentListParentDeptsByDeptResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("查询部门的所有上级父部门路径时出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String LIST_PARENT_API = "https://oapi.dingtalk.com/department/list_parent_depts";
    /**
     * 查询指定用户的所有上级父部门路径
     * https://oapi.dingtalk.com/department/list_parent_depts?access_token=ACCESS_TOKEN&userId=USERID
     *
     * @parem departId  希望查询的部门的id，包含查询的部门本身
     */
    public static DingtalkResponse<OapiDepartmentListParentDeptsResponse  > getDepartmentListParentDeptsByUser(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(LIST_PARENT_API);
            OapiDepartmentListParentDeptsRequest  request = new OapiDepartmentListParentDeptsRequest();
            request.setUserId(userId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentListParentDeptsResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("查询指定用户的所有上级父部门路径时出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ORG_USER_COUNT_API = "https://oapi.dingtalk.com/user/get_org_user_count";
    /**
     * 查询指定用户的所有上级父部门路径
     * https://oapi.dingtalk.com/user/get_org_user_count?access_token=ACCESS_TOKEN&onlyActive=0
     *
     * @parem active  0：包含未激活钉钉的人员数量  1：不包含未激活钉钉的人员数量
     */
    public static DingtalkResponse<OapiUserGetOrgUserCountResponse> getUserGetOrgUserCount (long active) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ORG_USER_COUNT_API);
            OapiUserGetOrgUserCountRequest   request = new OapiUserGetOrgUserCountRequest();
            request.setOnlyActive(active);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetOrgUserCountResponse  response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("查询指定用户的所有上级父部门路径时出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String CREATE_DEPT_API = "https://oapi.dingtalk.com/department/create";
    /**
     * 创建部门
     * https://oapi.dingtalk.com/department/create?access_token=ACCESS_TOKEN
     *
     * @parem DepartmentDto 部门对象DTO
     */
    public static DingtalkResponse<OapiDepartmentCreateResponse> create(DepartmentDto departmentDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_DEPT_API);
            OapiDepartmentCreateRequest request = new OapiDepartmentCreateRequest();
            ObjectKit.copyFields(departmentDto, request);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiDepartmentCreateResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("创建部门时出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String UPDATE_DEPT_API = "https://oapi.dingtalk.com/department/update";
    /**
     * 更新部门
     * https://oapi.dingtalk.com/department/create?access_token=ACCESS_TOKEN
     *
     * @parem DepartmentDto 部门对象DTO
     */
    public static DingtalkResponse<OapiDepartmentUpdateResponse> update(DepartmentDto departmentDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UPDATE_DEPT_API);
            OapiDepartmentUpdateRequest request = new OapiDepartmentUpdateRequest();
            ObjectKit.copyFields(departmentDto, request);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiDepartmentUpdateResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("更新部门时出错："+ e.getMessage(), e);
            return null;
        }
    }

    private static final String DELETE_DEPT_API = "https://oapi.dingtalk.com/department/delete";
    /**
     * 更新部门
     * https://oapi.dingtalk.com/department/delete?access_token=ACCESS_TOKEN&id=ID
     *
     * @parem id  部门id(注：不能删除根部门；不能删除含有子部门、成员的部门)
     */
    public static DingtalkResponse<OapiDepartmentDeleteResponse> delete(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DELETE_DEPT_API);
            OapiDepartmentDeleteRequest request = new OapiDepartmentDeleteRequest();
            request.setId(departId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiDepartmentDeleteResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("更新部门时出错："+ e.getMessage(), e);
            return null;
        }
    }
}
