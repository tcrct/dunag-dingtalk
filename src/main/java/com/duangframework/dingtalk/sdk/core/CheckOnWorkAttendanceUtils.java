package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.exception.ServiceException;
import com.duangframework.mvc.http.enums.HttpMethod;

import java.util.Date;
import java.util.List;

/**
 * 考勤
 * @author laotang
 * @date 2019-05-15
 */
public class CheckOnWorkAttendanceUtils {

    private static final String ENTERPRISE_CHECK_ON_WORK_ATTENDANCE_SCHEDULING_DETAILS_API = "https://oapi.dingtalk.com/topapi/attendance/listschedule";
    /**
     *   企业考勤排班详情
     *
     *   https://oapi.dingtalk.com/topapi/attendance/listschedule?access_token=ACCESS_TOKEN
     *
     *  @param workDate 排班时间，只取年月日部分
     *  @param offset 偏移位置，从0开始，后续传offset+size的值。当返回结果中的has_more为false时，表示没有多余的数据了。
     *  @param size 分页大小，最大200，默认值：200
     */
    public static DingtalkResponse<OapiAttendanceListscheduleResponse> enterpriseCheckOnWorkAttendanceSchedulngDetails(Date workDate, long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(ENTERPRISE_CHECK_ON_WORK_ATTENDANCE_SCHEDULING_DETAILS_API);
            OapiAttendanceListscheduleRequest request = new OapiAttendanceListscheduleRequest();
            request.setWorkDate(workDate);
            request.setOffset(offset);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceListscheduleResponse execute = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(execute);
        } catch (Exception e) {
            throw new ServiceException("企业考勤排班详情时出错: " + e.getMessage(), e);
        }
    }

    private static final String ENTERPRISE_CHECK_ON_WORK_ATTENDANCE_GROUP_DETAILS_API = "https://oapi.dingtalk.com/topapi/attendance/getsimplegroups";
    /**
     *   企业考勤组详情
     *
     *   https://oapi.dingtalk.com/topapi/attendance/getsimplegroups?access_token=ACCESS_TOKEN
     *
     *  @param offset 偏移位置，从0、1、2...依次递增，默认值：0
     *  @param size 分页大小，最大10，默认值：10
     */
    public static DingtalkResponse<OapiAttendanceGetsimplegroupsResponse> enterpriseCheckOnWorkAttendanceGroupDetails(long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(ENTERPRISE_CHECK_ON_WORK_ATTENDANCE_GROUP_DETAILS_API);
            OapiAttendanceGetsimplegroupsRequest request = new OapiAttendanceGetsimplegroupsRequest();
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceGetsimplegroupsResponse execute = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(execute);
        } catch (Exception e) {
            throw new ServiceException("企业考勤排班详情时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_HIT_CARD_DETAILS_API = "https://oapi.dingtalk.com/attendance/listRecord";
    /**
     *   获取打卡详情
     *
     *   https://oapi.dingtalk.com/attendance/listRecord?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param userIds 企业内的员工id列表，最多不能超过50个
     *  @param checkDateFrom 查询考勤打卡记录的起始工作日。格式为“yyyy-MM-dd hh:mm:ss”。
     *  @param checkDateTo 查询考勤打卡记录的结束工作日。格式为“yyyy-MM-dd hh:mm:ss”。注意，起始与结束工作日最多相隔7天
     *  @param isI18n 取值为true和false，表示是否为海外企业使用，默认为false。其中，true：海外平台使用，false：国内平台使用
     */
    public static DingtalkResponse<OapiAttendanceListRecordResponse> getHitCardDetails(String accessToken, List<String> userIds, String checkDateFrom, String checkDateTo, String isI18n) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_HIT_CARD_DETAILS_API);
            OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
            request.setCheckDateFrom(checkDateFrom);
            request.setCheckDateTo(checkDateTo);
            request.setUserIds(userIds);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceListRecordResponse execute = client.execute(request,accessToken);
            return new DingtalkResponse<>(execute);
        } catch (Exception e) {
            throw new ServiceException("获取打卡详情时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_HIT_CARD_RESULT_API = "https://oapi.dingtalk.com/attendance/list";
    /**
     *   获取打卡结果
     *
     *   https://oapi.dingtalk.com/attendance/list?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param workDateFrom 查询考勤打卡记录的起始工作日。格式为“yyyy-MM-dd HH:mm:ss”，HH:mm:ss可以使用00:00:00，具体查询的时候不会起作用，最后将返回此日期从0点到24点的结果
     *  @param workDateTo 查询考勤打卡记录的结束工作日。格式为“yyyy-MM-dd HH:mm:ss”，HH:mm:ss可以使用00:00:00，具体查询的时候不会起作用，最后将返回此日期从0点到24点的结果。注意，起始与结束工作日最多相隔7天
     *  @param userIdList 员工在企业内的UserID列表，企业用来唯一标识用户的字段。最多不能超过50个
     *  @param offset 表示获取考勤数据的起始点，第一次传0，如果还有多余数据，下次获取传的offset值为之前的offset+limit，0、1、2...依次递增
     *  @param limit 表示获取考勤数据的条数，最大不能超过50条
     *  @param isI18n 取值为true和false，表示是否为海外企业使用，默认为false。其中，true：海外平台使用，false：国内平台使用
     *
     */
    public static DingtalkResponse<OapiAttendanceListResponse> getHitCardResult(String accessToken, String workDateFrom, String workDateTo, List<String> userIdList, long offset,long limit, String isI18n) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_HIT_CARD_RESULT_API);
            OapiAttendanceListRequest request = new OapiAttendanceListRequest();
            request.setWorkDateFrom(workDateFrom);
            request.setWorkDateTo(workDateTo);
            request.setUserIdList(userIdList);
            request.setOffset(offset);
            request.setLimit(limit);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceListResponse response = client.execute(request,accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取打卡详情时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_ASK_FOR_LEAVE_DURATION_API = "https://oapi.dingtalk.com/topapi/attendance/getleaveapproveduration";
    /**
     *   获取请假时长
     *
     *   https://oapi.dingtalk.com/topapi/attendance/getleaveapproveduration?access_token=ACCESS_TOKEN
     *
     *  @param userId 员工在企业内的UserID，企业用来唯一标识用户的字段
     *  @param fromDate 请假开始时间
     *  @param toDate 请假结束时间
     */
    public static DingtalkResponse<OapiAttendanceGetleaveapprovedurationResponse> getAskForLeaveDuration(String userId, Date fromDate, Date toDate) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_ASK_FOR_LEAVE_DURATION_API);
            OapiAttendanceGetleaveapprovedurationRequest request = new OapiAttendanceGetleaveapprovedurationRequest();
            request.setFromDate(fromDate);
            request.setToDate(toDate);
            request.setUserid(userId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceGetleaveapprovedurationResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取请假时长时出错: " + e.getMessage(), e);
        }
    }

    private static final String QUERY_ASK_FOR_LEAVE_STATE_API = "https://oapi.dingtalk.com/topapi/attendance/getleavestatus";
    /**
     *   查询请假状态
     *
     *   https://oapi.dingtalk.com/topapi/attendance/getleavestatus?access_token=ACCESS_TOKEN
     *
     *  @param useridList 待查询用户id列表，支持最多100个用户的批量查询
     *  @param startTime 开始时间 ，UNIX时间戳，支持最多180天的查询
     *  @param endTime 结束时间 ，UNIX时间戳，支持最多180天的查询时间
     *  @param offset 分页偏移，非负整数
     *  @param size 分页大小，正整数，最大20
     */
    public static DingtalkResponse<OapiAttendanceGetleavestatusResponse> queryAskForLeaveState(String useridList, long startTime, long endTime, long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(QUERY_ASK_FOR_LEAVE_STATE_API);
            OapiAttendanceGetleavestatusRequest req = new OapiAttendanceGetleavestatusRequest();
            req.setUseridList(useridList);
            req.setStartTime(startTime);
            req.setEndTime(endTime);
            req.setOffset(offset);
            req.setSize(size);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceGetleavestatusResponse rsp = client.execute(req, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            throw new ServiceException("查询请假状态时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_USER_CHECK_ON_WORK_ATTENDANCE_GROUP_API = "https://oapi.dingtalk.com/topapi/attendance/getusergroup";
    /**
     *   获取用户考勤组
     *
     *   https://oapi.dingtalk.com/topapi/attendance/getusergroup?access_token=ACCESS_TOKEN
     *
     *  @param userId 员工在企业内的UserID，企业用来唯一标识用户的字段
     */
    public static DingtalkResponse<OapiAttendanceGetusergroupResponse> getUserCheckOnWorkAttendanceGroup(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_CHECK_ON_WORK_ATTENDANCE_GROUP_API);
            OapiAttendanceGetusergroupRequest request = new OapiAttendanceGetusergroupRequest();
            request.setUserid(userId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiAttendanceGetusergroupResponse execute = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(execute);
        } catch (Exception e) {
            throw new ServiceException("获取用户考勤组时出错: " + e.getMessage(), e);
        }
    }
}
