package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.dingtalk.utils.DingTalkUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    private static final String GET_ROLE_UNDER_LIST_API = "https://oapi.dingtalk.com/topapi/role/simplelist";
    /**
     *   获取角色下的员工列表
     *
     *   https://oapi.dingtalk.com/topapi/role/simplelist?access_token=ACCESS_TOKEN
     *
     * @param  roleId   角色ID
     * @param  size      分页大小，默认值：20，最大值200
     * @param  offset    分页偏移，默认值：0
     */
    public static DingtalkResponse<OapiRoleSimplelistResponse> getRoleUnderList(long offset, long size, long roleId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ROLE_UNDER_LIST_API);
            OapiRoleSimplelistRequest request = new OapiRoleSimplelistRequest();
            request.setRoleId(roleId);
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleSimplelistResponse response = client.execute(request,  AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取角色下的员工列表时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ROLE_GROUP_API = "https://oapi.dingtalk.com/topapi/role/getrolegroup";
    /**
     *   获取角色组
     *
     *   https://oapi.dingtalk.com/topapi/role/getrolegroup?access_token=ACCESS_TOKEN
     *
     * @param groupId 角色组
     */
    public static DingtalkResponse<OapiRoleGetrolegroupResponse> getRoleGroup(long groupId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ROLE_GROUP_API);
            OapiRoleGetrolegroupRequest request = new OapiRoleGetrolegroupRequest();
            request.setGroupId(groupId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleGetrolegroupResponse response = client.execute(request,  AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取角色组时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ROLE_DETAILS_API = "https://oapi.dingtalk.com/topapi/role/getrole";
    /**
     *   获取角色详情
     *
     *   https://oapi.dingtalk.com/topapi/role/getrole?access_token=ACCESS_TOKEN
     *
     * @param roleId 角色Id
     */
    public static DingtalkResponse<OapiRoleGetroleResponse> getRoleDetails(long roleId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ROLE_DETAILS_API);
            OapiRoleGetroleRequest req = new OapiRoleGetroleRequest();
            req.setRoleId(roleId);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiRoleGetroleResponse rsp = client.execute(req, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            logger.warn("获取角色详情时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ADD_ROLE_API = "https://oapi.dingtalk.com/role/add_role";
    /**
     *   创建角色
     *
     *   https://oapi.dingtalk.com/role/add_role?access_token=ACCESS_TOKEN
     *
     * @param roleName 角色名称
     * @param groupId 角色组id
     */
    public static DingtalkResponse<OapiRoleAddroleResponse> createRole(long groupId, String roleName) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ADD_ROLE_API);
            OapiRoleAddroleRequest req = new OapiRoleAddroleRequest();
            req.setRoleName(roleName);
            req.setGroupId(groupId);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiRoleAddroleResponse rsp = client.execute(req, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            logger.warn("创建角色时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_UPDATE_ROLE_API = "https://oapi.dingtalk.com/role/update_role";
    /**
     *   更新角色
     *
     *   https://oapi.dingtalk.com/role/update_role?access_token=ACCESS_TOKEN
     *
     * @param roleName 角色名称
     * @param roleId 角色id
     */
    public static DingtalkResponse<OapiRoleUpdateroleResponse> updateRole(long roleId, String roleName) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_UPDATE_ROLE_API);
            OapiRoleUpdateroleRequest req = new OapiRoleUpdateroleRequest();
            req.setRoleId(roleId);
            req.setRoleName(roleName);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiRoleUpdateroleResponse rsp = client.execute(req, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            logger.warn("更新角色时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_DELETE_ROLE_API = "https://oapi.dingtalk.com/topapi/role/deleterole";
    /**
     *   删除角色
     *
     *   https://oapi.dingtalk.com/topapi/role/deleterole?access_token=ACCESS_TOKEN
     *
     * @param roleId 角色id
     */
    public static DingtalkResponse<OapiRoleDeleteroleResponse> deleteRole(long roleId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_DELETE_ROLE_API);
            OapiRoleDeleteroleRequest request = new OapiRoleDeleteroleRequest();
            request.setRoleId(roleId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleDeleteroleResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("删除角色时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ADD_ROLE_GROUP_API = "https://oapi.dingtalk.com/role/add_role_group";
    /**
     *   创建角色组
     *
     *   https://oapi.dingtalk.com/role/add_role_group?access_token=ACCESS_TOKEN
     *
     * @param name 角色组名称
     */
    public static DingtalkResponse<OapiRoleAddrolegroupResponse> createRoleGroup(String name) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ADD_ROLE_GROUP_API);
            OapiRoleAddrolegroupRequest req = new OapiRoleAddrolegroupRequest();
            req.setName(name);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiRoleAddrolegroupResponse rsp = client.execute(req, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            logger.warn("创建角色组时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ADD_ROLES_API = "https://oapi.dingtalk.com/topapi/role/addrolesforemps";
    /**
     *   批量增加员工角色
     *
     *   https://oapi.dingtalk.com/topapi/role/addrolesforemps?access_token=ACCESS_TOKEN
     *
     * @param roleIds 角色id list，最大列表长度：20
     * @param userIds 员工id list，最大列表长度：100
     */
    public static DingtalkResponse<OapiRoleAddrolesforempsResponse> batchCreateRoles(List<String> roleIds, List<String> userIds) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ADD_ROLES_API);
            OapiRoleAddrolesforempsRequest request = new OapiRoleAddrolesforempsRequest();

            request.setRoleIds(DingTalkUtils.collections2String(roleIds, ","));
            request.setUserIds(DingTalkUtils.collections2String(userIds, ","));
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleAddrolesforempsResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn(" 批量增加员工角色时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_REMOVE_ROLES_API = "https://oapi.dingtalk.com/topapi/role/removerolesforemps";
    /**
     *   批量删除员工角色
     *
     *   https://oapi.dingtalk.com/topapi/role/removerolesforemps?access_token=ACCESS_TOKEN
     *
     *  @param roleIds 角色id list，最大列表长度：20
     * @param userIds 员工id list，最大列表长度：100
     */
    public static DingtalkResponse<OapiRoleRemoverolesforempsResponse> batchRemoveRoles(List<String> roleIds, List<String> userIds) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_REMOVE_ROLES_API);
            OapiRoleRemoverolesforempsRequest request = new OapiRoleRemoverolesforempsRequest();
            request.setRoleIds(DingTalkUtils.collections2String(roleIds,","));
            request.setUserIds(DingTalkUtils.collections2String(userIds,","));
            request.setHttpMethod(HttpMethod.POST.name());
            OapiRoleRemoverolesforempsResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn(" 批量删除员工角色时出错: " + e.getMessage(), e);
            return null;
        }
    }

}
