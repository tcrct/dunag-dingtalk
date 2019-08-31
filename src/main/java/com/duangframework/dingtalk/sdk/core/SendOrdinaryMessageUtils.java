package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;
import com.dingtalk.api.response.OapiMessageSendToConversationResponse;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.sdk.dto.MessageBodyDto;
import com.duangframework.dingtalk.utils.DingTalkAccessTokenUtils;
import com.duangframework.exception.ServiceException;
import com.duangframework.mvc.http.enums.HttpMethod;

/**
 * 发送普通消息
 * @author laotang
 * @date 2019-05-15
 */
public class SendOrdinaryMessageUtils {

    private static final String SEND_ORDINARY_MESSAGE_API = "https://oapi.dingtalk.com/message/send_to_conversation";
    /**
     *   发送普通消息
     *
     *   https://oapi.dingtalk.com/message/send_to_conversation?access_token=ACCESS_TOKEN
     *
     *   @param sender  消息发送者 userId
     *   @param cid 群会话或者个人会话的id，通过JSAPI的dd.chooseChatForNormalMsg接口唤起联系人界面选择之后即可拿到会话cid
     *   @param bodyDto 消息内容，消息类型和样例可参考“消息类型与数据格式”文档。最长不超过2048个字节
     *
     */
    public static DingtalkResponse<OapiMessageSendToConversationResponse> sendOrdinaryMessage(String sender, String cid, MessageBodyDto bodyDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(SEND_ORDINARY_MESSAGE_API);
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
            OapiMessageSendToConversationRequest req = new OapiMessageSendToConversationRequest();
            req.setSender(sender);
            req.setCid(cid);
            OapiMessageSendToConversationRequest.Msg msgs = new OapiMessageSendToConversationRequest.Msg();
            msgs.setMsgtype(bodyDto.getType().name().toLowerCase());
            if(MessageBodyDto.Type.TEXT.equals(bodyDto.getType())) {
                // 文本消息
                OapiMessageSendToConversationRequest.Text text = new OapiMessageSendToConversationRequest.Text();
                text.setContent(bodyDto.getContent());
                msgs.setText(new OapiMessageSendToConversationRequest.Text());
            }
            else if(MessageBodyDto.Type.IMAGE.equals(bodyDto.getType())) {
                // 图片
                OapiMessageSendToConversationRequest.Image image = new OapiMessageSendToConversationRequest.Image();
                image.setMediaId("@lADOdvRYes0CbM0CbA");
                msgs.setImage(new OapiMessageSendToConversationRequest.Image());
            }
            else if(MessageBodyDto.Type.FILE.equals(bodyDto.getType())) {
                // 文件
                OapiMessageSendToConversationRequest.File file = new OapiMessageSendToConversationRequest.File();
                file.setMediaId("@lADOdvRYes0CbM0CbA");
                msgs.setFile(new OapiMessageSendToConversationRequest.File());
            }
            else if(MessageBodyDto.Type.LINK.equals(bodyDto.getType())) {
                OapiMessageSendToConversationRequest.Link link = new OapiMessageSendToConversationRequest.Link();
                link.setMessageUrl("https://www.baidu.com/");
                link.setPicUrl("@lADOdvRYes0CbM0CbA");
                link.setText("步扬测试");
                link.setTitle("oapi");
                msgs.setLink(link);
            }
            else if(MessageBodyDto.Type.MARKDOWN.equals(bodyDto.getType())) {
                OapiMessageSendToConversationRequest.Markdown markdown = new OapiMessageSendToConversationRequest.Markdown();
                markdown.setText("# 这是支持markdown的文本 \\n## 标题2  \\n* 列表1 \\n![alt 啊](https://gw.alipayobjects.com/zos/skylark-tools/public/files/b424a1af2f0766f39d4a7df52ebe0083.png)");
                markdown.setTitle("首屏会话透出的展示内容");
                msgs.setMarkdown(markdown);
            }
            else if(MessageBodyDto.Type.ACTION_CARD.equals(bodyDto.getType())) {
                OapiMessageSendToConversationRequest.ActionCard actionCard = new OapiMessageSendToConversationRequest.ActionCard();
                actionCard.setTitle("是透出到会话列表和通知的文案");
                actionCard.setMarkdown("持markdown格式的正文内");
                actionCard.setSingleTitle("查看详情");
                actionCard.setSingleUrl("https://open.dingtalk.com");
                msgs.setActionCard(actionCard);
            } else {
                throw new IllegalArgumentException("参数异常");
            }
            req.setMsg(msgs);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiMessageSendToConversationResponse response = client.execute(req, DingTalkAccessTokenUtils.getAccessToken());
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("发送工作通知消息时出错: " + e.getMessage(), e);
        }
    }
}
