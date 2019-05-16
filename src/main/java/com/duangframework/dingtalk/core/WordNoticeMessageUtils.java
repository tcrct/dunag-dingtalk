package com.duangframework.dingtalk.core;

import com.aliyun.oss.ServiceException;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.dto.MessageBodyDto;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.dingtalk.utils.DingTalkUtils;
import com.duangframework.mvc.http.enums.HttpMethod;
import java.util.List;

/**
 * 工作通知消息
 * @author laotang
 * @date 2019-05-15
 */
public class WordNoticeMessageUtils {

    private static final String SEND_OUT_WORD_NOTICE_MESSAGE_API = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

    public static DingtalkResponse<OapiMessageCorpconversationAsyncsendV2Response> sendOutWordNoticeMessage(long agentId, List<String> useridList, Boolean toAllUser, MessageBodyDto bodyDto) {
        return sendOutWordNoticeMessage(agentId,useridList,null, toAllUser,bodyDto);
    }
    /**
     *   发送工作通知消息
     *
     *   https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?access_token=ACCESS_TOKEN
     *
     *   @param agentId  应用agentId
     *   @param useridList 接收者的用户userid列表，最大列表长度：100
     *   @param deptIdList 接收者的部门id列表，最大列表长度：20,  接收者是部门id下(包括子部门下)的所有用户
     *   @param toAllUser 是否发送给企业全部用户
     *   @param bodyDto 消息内容，消息类型和样例参考“消息类型与数据格式”。最长不超过2048个字节
     *
     */
    public static DingtalkResponse<OapiMessageCorpconversationAsyncsendV2Response> sendOutWordNoticeMessage(long agentId, List<String> useridList, List<String> deptIdList, Boolean toAllUser, MessageBodyDto bodyDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(SEND_OUT_WORD_NOTICE_MESSAGE_API);
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
            request.setUseridList(DingTalkUtils.collections2String(useridList,","));
            if(null != deptIdList && !deptIdList.isEmpty()) {
                request.setDeptIdList(DingTalkUtils.collections2String(deptIdList,","));
            }
            request.setAgentId(agentId);
            request.setToAllUser(toAllUser);

            OapiMessageCorpconversationAsyncsendV2Request.Msg msgs = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msgs.setMsgtype(bodyDto.getType().name().toLowerCase());
            if(MessageBodyDto.Type.TEXT.equals(bodyDto.getType())) {
                msgs.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
                msgs.getText().setContent(bodyDto.getContent());
            }
            else if(MessageBodyDto.Type.IMAGE.equals(bodyDto.getType())) {
                msgs.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
                msgs.getImage().setMediaId(bodyDto.getContent());
            }
            else if(MessageBodyDto.Type.FILE.equals(bodyDto.getType())) {
                msgs.setFile(new OapiMessageCorpconversationAsyncsendV2Request.File());
                msgs.getFile().setMediaId(bodyDto.getContent());
            }
            else if(MessageBodyDto.Type.LINK.equals(bodyDto.getType())) {
                msgs.setMsgtype("link");
                msgs.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
                msgs.getLink().setTitle("test");
                msgs.getLink().setText("test");
                msgs.getLink().setMessageUrl("test");
                msgs.getLink().setPicUrl("test");
            }
            else if(MessageBodyDto.Type.MARKDOWN.equals(bodyDto.getType())) {
                msgs.setMsgtype("markdown");
                msgs.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
                msgs.getMarkdown().setText("##### text");
                msgs.getMarkdown().setTitle("### Title");
            }
            else if(MessageBodyDto.Type.OA.equals(bodyDto.getType())) {
                msgs.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
                msgs.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
                msgs.getOa().getHead().setText("head");
                msgs.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
                msgs.getOa().getBody().setContent("xxx");
                msgs.setMsgtype("oa");
            }
            else if(MessageBodyDto.Type.ACTION_CARD.equals(bodyDto.getType())) {
                msgs.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
                msgs.getActionCard().setTitle("xxx123411111");
                msgs.getActionCard().setMarkdown("### 测试123111");
                msgs.getActionCard().setSingleTitle("测试测试");
                msgs.getActionCard().setSingleUrl("https://www.baidu.com");
                msgs.setMsgtype("action_card");
            } else {
                throw new IllegalArgumentException("参数异常");
            }
            request.setMsg(msgs);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("发送工作通知消息时出错: " + e.getMessage(), e);
        }
    }

    private static final String QUERY_WORD_NOTICE_MESSAGE_SEND_OUT_SPEED_OF_PROGRESS_API = "https://oapi.dingtalk.com/topapi/message/corpconversation/getsendprogress";
    /**
     *   查询工作通知消息的发送进度
     *
     *   https://oapi.dingtalk.com/topapi/message/corpconversation/getsendprogress?access_token=ACCESS_TOKEN
     *
     *   @param agentId  发送消息时使用的微应用的id
     *   @param taskId 发送消息时钉钉返回的任务id
     */
    public static DingtalkResponse<OapiMessageCorpconversationGetsendprogressResponse> queryWordNoticeMessageSendOutSpeedOfProgress(long agentId,long taskId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(QUERY_WORD_NOTICE_MESSAGE_SEND_OUT_SPEED_OF_PROGRESS_API);
            OapiMessageCorpconversationGetsendprogressRequest request  = new OapiMessageCorpconversationGetsendprogressRequest();
            request.setAgentId(agentId);
            request.setTaskId(taskId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiMessageCorpconversationGetsendprogressResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("查询工作通知消息的发送进度时出错: " + e.getMessage(), e);
        }
    }

    private static final String QUERY_WORD_NOTICE_MESSAGE_SEND_OUT_RESULT_API = "https://oapi.dingtalk.com/topapi/message/corpconversation/getsendresult";
    /**
     *   查询工作通知消息的发送结果
     *
     *   https://oapi.dingtalk.com/topapi/message/corpconversation/getsendresult?access_token=ACCESS_TOKEN
     *
     *   @param agentId  微应用的agentid
     *   @param taskId 异步任务的id
     */
    public static DingtalkResponse<OapiMessageCorpconversationGetsendresultResponse> queryWordNoticeMessageSendOutResult(long agentId,long taskId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(QUERY_WORD_NOTICE_MESSAGE_SEND_OUT_RESULT_API);
            OapiMessageCorpconversationGetsendresultRequest request  = new OapiMessageCorpconversationGetsendresultRequest();
            request.setAgentId(agentId);
            request.setTaskId(taskId);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiMessageCorpconversationGetsendresultResponse response = client.execute(request, AuthUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("查询工作通知消息的发送结果时出错: " + e.getMessage(), e);
        }
    }
}
