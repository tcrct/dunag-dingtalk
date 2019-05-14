package com.duangframework.dingtalk.kit;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.dto.UserDto;
import com.duangframework.dingtalk.dto.UserUpdateDto;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.kit.ObjectKit;
import com.duangframework.kit.ToolsKit;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 * https://open-doc.dingtalk.com/microapp/serverapi2/ege851
 * @author  laotang
 * @date 2019/5/9.
 */
public class UserUtils {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentUtils.class);


    private static final String GET_USER_API = "https://oapi.dingtalk.com/user/get";
    /**
     *   获取用户详情
     *
     *   https://oapi.dingtalk.com/user/get?access_token=ACCESS_TOKEN&userid=zhangsan
     *
     * @param userId 员工ID
     */
    public static DingtalkResponse<OapiUserGetResponse> getUser(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_API);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取用户详情时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_DEPT_USER_API = "https://oapi.dingtalk.com/user/getDeptMember";
    /**
     *   获取部门用户userid列表
     *   通过该接口，可以获取当前部门下的userid列表
     *   https://oapi.dingtalk.com/user/getDeptMember?access_token=ACCESS_TOKEN&deptId=1
     *
     * @param deptId 部门ID
     */
    public static DingtalkResponse<OapiUserGetDeptMemberResponse> getDetpUser(String deptId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_DEPT_USER_API);
            OapiUserGetDeptMemberRequest  request = new OapiUserGetDeptMemberRequest();
            request.setDeptId(deptId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetDeptMemberResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门用户userid列表时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_SIMPLE_LIST_API = "https://oapi.dingtalk.com/user/simplelist";
    /**
     *   获取部门用户
     *   通过该接口，可以获取当前部门下的userid列表
     *   https://oapi.dingtalk.com/user/simplelist?access_token=ACCESS_TOKEN&department_id=1
     *
     * @param deptId 部门ID
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size 持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
     * @param order 支持分页查询，部门成员的排序规则，默认不传是按自定义排序；<br/>
     *              entry_asc：代表按照进入部门的时间升序，
     *              entry_desc：代表按照进入部门的时间降序，
     *              modify_asc：代表按照部门信息修改时间升序，
     *              modify_desc：代表按照部门信息修改时间降序，
     *              custom：代表用户定义(未定义时按照拼音)排序
     */
    public static DingtalkResponse<OapiUserSimplelistResponse> getDetpUser(long deptId, long offset, long size, String... order) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_SIMPLE_LIST_API);
            OapiUserSimplelistRequest  request = new OapiUserSimplelistRequest();
            request.setDepartmentId(deptId);
            request.setOffset(offset);
            request.setSize(size);
            if(ToolsKit.isNotEmpty(order)) {
                request.setOrder(order[0]);
            }
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserSimplelistResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_USER_LIST_DETAILS_API = "https://oapi.dingtalk.com/user/listbypage";
    /**
     *   获取部门用户详情
     *   通过该接口，可以获取当前部门下的userid列表
     *   https://oapi.dingtalk.com/user/listbypage?access_token=ACCESS_TOKEN&department_id=1
     *
     * @param deptId 部门ID
     * @param offset 支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
     * @param size 持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
     * @param order 支持分页查询，部门成员的排序规则，默认不传是按自定义排序；<br/>
     *              entry_asc：代表按照进入部门的时间升序，
     *              entry_desc：代表按照进入部门的时间降序，
     *              modify_asc：代表按照部门信息修改时间升序，
     *              modify_desc：代表按照部门信息修改时间降序，
     *              custom：代表用户定义(未定义时按照拼音)排序
     */
    public static DingtalkResponse<OapiUserListbypageResponse> getDetpUserDetails(long deptId, long offset, long size, String... order) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_LIST_DETAILS_API);
            OapiUserListbypageRequest request = new OapiUserListbypageRequest();
            request.setOffset(offset);
            request.setSize(size);
            request.setDepartmentId(deptId);
            if(ToolsKit.isNotEmpty(order)) {
                request.setOrder(order[0]);
            }
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserListbypageResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ADMIN_API = "https://oapi.dingtalk.com/user/get_admin";
    /**
     *   获取管理员列表
     *   https://oapi.dingtalk.com/user/get_admin?access_token=ACCESS_TOKEN
     */
    public static DingtalkResponse<OapiUserGetAdminResponse> getAdmin() {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_LIST_DETAILS_API);
            OapiUserGetAdminRequest request = new OapiUserGetAdminRequest();
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetAdminResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取部门用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_ADMIN_SCOPE_API = "https://oapi.dingtalk.com/topapi/user/get_admin_scope";
    /**
     *   获取管理员通讯录权限范围
     *   https://oapi.dingtalk.com/topapi/user/get_admin_scope?access_token=ACCESS_TOKEN
     *
     * @param userId 员工ID
     */
    public static DingtalkResponse<OapiUserGetAdminScopeResponse> getAdminSocpe(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ADMIN_SCOPE_API);
            OapiUserGetAdminScopeRequest request = new OapiUserGetAdminScopeRequest();
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetAdminScopeResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取管理员通讯录权限范围时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_USERID_UNIONID_API = "https://oapi.dingtalk.com/user/get_admin_scope";
    /**
     *   根据unionid获取userid
     *   https://oapi.dingtalk.com/topapi/user/get_admin_scope?access_token=ACCESS_TOKEN
     *
     * @param unionId 员工ID
     */
    public static DingtalkResponse<OapiUserGetUseridByUnionidResponse > getUseridByUnionid(String unionId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USERID_UNIONID_API);
            OapiUserGetUseridByUnionidRequest  request = new OapiUserGetUseridByUnionidRequest();
            request.setUnionid(unionId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserGetUseridByUnionidResponse  response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("根据unionid获取userid时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String CREATE_USER_API = "https://oapi.dingtalk.com/user/create";
    /**
     *   创建用户
     *   https://oapi.dingtalk.com/user/create?access_token=ACCESS_TOKEN
     *
     * @param userDto 员工DTO
     */
    public static DingtalkResponse<OapiUserCreateResponse> createUser(UserDto userDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_USER_API);
            OapiUserCreateRequest request = new OapiUserCreateRequest ();
            ObjectKit.copyFields(userDto, request);
            if(ToolsKit.isNotEmpty(userDto.getDepartmentList())) {
                // 需要用字符串， "[100,200]" 这种格式
                request.setDepartment(JSON.toJSONString(userDto.getDepartmentList()));
            }
            request.setHttpMethod(HttpMethod.POST.name());
            OapiUserCreateResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("创建用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String UPDATE_USER_API = "https://oapi.dingtalk.com/user/update";
    /**
     *   更新用户
     *   https://oapi.dingtalk.com/user/update?access_token=ACCESS_TOKEN&userid=zhangsan
     *
     * @param userDto 员工更新DTO
     */
    public static DingtalkResponse<OapiUserUpdateResponse> updateUser(UserUpdateDto userDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UPDATE_USER_API);
            OapiUserUpdateRequest request = new OapiUserUpdateRequest();
            ObjectKit.copyFields(userDto, request);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiUserUpdateResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("创建用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String DELETE_USER_API = "https://oapi.dingtalk.com/user/delete";
    /**
     *   更新用户
     *   https://oapi.dingtalk.com/user/delete?access_token=ACCESS_TOKEN&userid=zhangsan
     *
     * @param userId 员工ID
     */
    public static DingtalkResponse<OapiUserDeleteResponse > updateUser(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DELETE_USER_API);
            OapiUserDeleteRequest request = new OapiUserDeleteRequest();
            request.setUserid(userId);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiUserDeleteResponse  response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("创建用户时出错: " + e.getMessage(), e);
            return null;
        }
    }

}
