package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportGetunreadcountRequest;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportGetunreadcountResponse;
import com.dingtalk.api.response.OapiReportListResponse;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 日志
 * @author laotang
 * @date 2019-05-14
 */
public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    private static final String GET_USER_LOG_API = "https://oapi.dingtalk.com/topapi/report/list";
    /**
     *  获取用户日志数据
     *
     *  https://oapi.dingtalk.com/topapi/report/list?access_token=ACCESS_TOKEN
     *
     * @param startTime  起始时间。时间的毫秒数
     * @param endTime   截止时间。时间的毫秒数，如：1520956800000
     * @param templateName 要查询的模板名称
     * @param userId 员工的userid
     * @param cursor 查询游标，初始传入0，后续从上一次的返回值中获取
     * @param size 每页数据量, 最大值是20
     *
     */
    public static DingtalkResponse<OapiReportListResponse> getUserLog(Date startTime, Date endTime, String templateName, String userId, long cursor, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_LOG_API);
            OapiReportListRequest request = new OapiReportListRequest();
            request.setUserid(userId);
            request.setStartTime(startTime.getTime());
            request.setEndTime(endTime.getTime());
            request.setCursor(cursor);
            request.setSize(size);
            request.setTemplateName(templateName);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiReportListResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取用户日志数据时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_SEE_USER_LOG_TEMPLATE_API = "https://oapi.dingtalk.com/topapi/report/template/listbyuserid";
    /**
     *  获取用户可见的日志模板
     *
     *  https://oapi.dingtalk.com/topapi/report/template/listbyuserid?access_token=ACCESS_TOKEN
     *
     * @param userId  用户id
     * @param offset  分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
     * @param size 分页大小，最大可设置成100
     *
     *
     */
    public static DingtalkResponse<OapiReportTemplateListbyuseridResponse> getLogTemplate(String userId, long offset, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_SEE_USER_LOG_TEMPLATE_API);
            OapiReportTemplateListbyuseridRequest req = new OapiReportTemplateListbyuseridRequest();
            req.setUserid(userId);
            req.setOffset(offset);
            req.setSize(size);
            req.setHttpMethod(HttpMethod.POST.name());
            OapiReportTemplateListbyuseridResponse rsp = client.execute(req, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(rsp);
        } catch (Exception e) {
            logger.warn("获取用户可见的日志模板时出错: " + e.getMessage(), e);
            return null;
        }
    }

    private static final String GET_USER_LOG_NOT_READ_API = "https://oapi.dingtalk.com/topapi/report/getunreadcount";
    /**
     *  获取用户日志未读数
     *
     *  https://oapi.dingtalk.com/topapi/report/getunreadcount?access_token=ACCESS_TOKEN
     *
     * @param userId  用户id
     *
     * @return
     */
    public static DingtalkResponse<OapiReportGetunreadcountResponse> getNotReadLog(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_USER_LOG_NOT_READ_API);
            OapiReportGetunreadcountRequest request = new OapiReportGetunreadcountRequest();
            request.setUserid(userId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiReportGetunreadcountResponse response = client.execute(request, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            logger.warn("获取用户日志未读数: " + e.getMessage(), e);
            return null;
        }
    }

}
