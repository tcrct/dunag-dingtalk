package com.duangframework.dingtalk.sdk.core;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.sdk.dto.MessageBodyDto;
import com.duangframework.exception.ServiceException;
import com.duangframework.mvc.http.enums.HttpMethod;
import java.util.List;

/**
 * 发送群消息
 * @author laotang
 * @date 2019-05-15
 */
public class SendGroupMessagesUtils {

    private static final String SEND_GROUP_MESSAGES_API = "https://oapi.dingtalk.com/chat/send";
    /**
     *   发送群消息
     *
     *   https://oapi.dingtalk.com/chat/send?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param chatid  群会话的id，可以在调用创建群会话接口的返回结果里面获取，也可以通过dd.chooseChat获取
     *  @param bodyDto 消息内容，消息类型和样例可参考“消息类型与数据格式”文档
     */
    public static DingtalkResponse<OapiChatSendResponse> sendGroupMessages(String accessToken, String chatid, MessageBodyDto bodyDto) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(SEND_GROUP_MESSAGES_API);
            OapiChatSendRequest request = new OapiChatSendRequest();
            request.setChatid(chatid);

            OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
            msg.setMsgtype("text");
            OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
            text.setContent(bodyDto.getContent());
            msg.setText(text);

            request.setMsg(msg);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiChatSendResponse  response = client.execute(request, accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("发送群消息时出错: " + e.getMessage(), e);
        }
    }

    private static final String QUERY_GROUP_MESSAGES_ALREADY_READ_LIST_API = "https://oapi.dingtalk.com/chat/getReadList";
    /**
     *   查询群消息已读人员列表
     *
     *   https://oapi.dingtalk.com/chat/getReadList?access_token=ACCESS_TOKEN&messageId=MESSAGEID&cursor=CURSOR&size=SIZE
     *
     *  @param accessToken 调用接口凭证
     *  @param messageId  发送群消息接口返回的加密消息id
     *  @param cursor 分页查询的游标，第一次可以传0，后续传返回结果中的next_cursor的值。当返回结果中，没有next_cursor时，表示没有后续的数据了，可以结束调用
     *  @param size 分页查询的大小，最大可以传100
     */
    public static DingtalkResponse<OapiChatGetReadListResponse > queryGroupMessagesAlreadyReadList(String accessToken, String messageId, long cursor, long size) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(QUERY_GROUP_MESSAGES_ALREADY_READ_LIST_API);
            OapiChatGetReadListRequest request = new OapiChatGetReadListRequest();
            request.setMessageId(messageId);
            request.setCursor(cursor);
            request.setSize(size);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiChatGetReadListResponse response = client.execute(request, accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("查询群消息已读人员列表时出错: " + e.getMessage(), e);
        }
    }

    private static final String CREATE_SESSION_API = "https://oapi.dingtalk.com/chat/create";
    /**
     *   创建会话
     *
     *   https://oapi.dingtalk.com/chat/create?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param name 群名称，长度限制为1~20个字符
     *  @param owner 群主userId，员工唯一标识ID；必须为该会话useridlist的成员之一
     *  @param useridlist 群成员列表，每次最多支持40人，群人数上限为1000
     *  @param showHistoryType 新成员是否可查看聊天历史消息（新成员入群是否可查看最近100条聊天记录）,0代表否，1代表是，不传默认为否
     *  @param searchable 群可搜索，0-默认，不可搜索，1-可搜索
     *  @param validationType  入群验证，0：不入群验证（默认） 1：入群验证
     *  @param mentionAllAuthority @all 权限，0-默认，所有人，1-仅群主可@all
     *  @param chatBannedType 群禁言，0-默认，不禁言，1-全员禁言
     *  @param managementType 管理类型，0-默认，所有人可管理，1-仅群主可管理
     */
    public static DingtalkResponse<OapiChatCreateResponse> createSession(String accessToken, String name, String owner, List<String> useridlist,
                          long showHistoryType, long searchable, long validationType, long mentionAllAuthority, long chatBannedType, long managementType) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(CREATE_SESSION_API);
            OapiChatCreateRequest request = new OapiChatCreateRequest();
            request.setName(name);
            request.setOwner(owner);
            request.setUseridlist(useridlist);
            request.setShowHistoryType(showHistoryType);
            request.setSearchable(searchable);
            request.setValidationType(validationType);
            request.setMentionAllAuthority(mentionAllAuthority);
            request.setChatBannedType(chatBannedType);
            request.setManagementType(managementType);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiChatCreateResponse response = client.execute(request, accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("创建会话时出错: " + e.getMessage(), e);
        }
    }

    private static final String UPDATE_SESSION_API = "https://oapi.dingtalk.com/chat/update";
    /**
     *   修改会话
     *
     *   https://oapi.dingtalk.com/chat/update?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param chatid 群会话的id
     *  @param name 群名称，长度限制为1~20个字符
     *  @param owner 群主userId，员工唯一标识ID；必须为该会话成员之一；不传则不修改
     *  @param addUseridlist 添加成员列表，每次最多支持40人，群人数上限为1000
     *  @param deluUseridlist 删除成员列表，每次最多支持40人，群人数上限为1000
     *  @param icon 群头像mediaid
     *  @param chatBannedType  群禁言，0-默认，不禁言，1-全员禁言
     *  @param searchable 群可搜索，0-默认，不可搜索，1-可搜索
     *  @param validationType 入群验证，0：不入群验证（默认） 1：入群验证
     *  @param mentionAllAuthority @all 权限，0-默认，所有人，1-仅群主可@all
     *  @param showHistoryType 新成员可查看聊天历史 0否 1是
     *  @param managementType 管理类型，0-默认，所有人可管理，1-仅群主可管理
     */
    public static DingtalkResponse<OapiChatUpdateResponse> updateSession(String accessToken, String chatid, String name, String owner, List<String> addUseridlist,List<String> deluUseridlist,
            String icon, long chatBannedType, long searchable, long validationType, long mentionAllAuthority, long showHistoryType, long managementType) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(UPDATE_SESSION_API);
            OapiChatUpdateRequest request = new OapiChatUpdateRequest();
            request.setChatid(chatid);
            request.setName(name);
            request.setOwner(owner);
            request.setAddUseridlist(addUseridlist);
            request.setDelUseridlist(deluUseridlist);
            request.setIcon(icon);
            request.setChatBannedType(chatBannedType);
            request.setSearchable(searchable);
            request.setValidationType(validationType);
            request.setMentionAllAuthority(mentionAllAuthority);
            request.setShowHistoryType(showHistoryType);
            request.setManagementType(managementType);
            request.setHttpMethod(HttpMethod.POST.name());
            OapiChatUpdateResponse response = client.execute(request, accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("修改会话时出错: " + e.getMessage(), e);
        }
    }

    private static final String GET_SESSION_API = "https://oapi.dingtalk.com/chat/get";
    /**
     *   获取会话
     *
     *   https://oapi.dingtalk.com/chat/get?access_token=ACCESS_TOKEN
     *
     *  @param accessToken 调用接口凭证
     *  @param chatid 群会话的id
     */
    public static DingtalkResponse<OapiChatGetResponse> getSession(String accessToken, String chatid) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(GET_SESSION_API);
            OapiChatGetRequest request = new OapiChatGetRequest();
            request.setChatid(chatid);
            request.setHttpMethod(HttpMethod.GET.name());
            OapiChatGetResponse response = client.execute(request, accessToken);
            return new DingtalkResponse<>(response);
        } catch (Exception e) {
            throw new ServiceException("获取会话时出错: " + e.getMessage(), e);
        }
    }
}
