package com.duangframework.dingtalk.service;

import com.duangframework.dingtalk.utils.DingTalkUtils;
import com.duangframework.exception.ServiceException;
import com.duangframework.kit.ToolsKit;
import com.duangframework.mvc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 回调处理器
 * @author laotang
 * @date 2019-05-15
 */
@Service
public class CallbackService {

    private final static Logger logger = LoggerFactory.getLogger(CallbackService.class);

    public String callback(String signature, String timeStamp, String nonce, String encrypt) {
        if(ToolsKit.isEmpty(signature) || ToolsKit.isEmpty(timeStamp) || ToolsKit.isEmpty(nonce) || ToolsKit.isEmpty(encrypt)) {
            throw new ServiceException("回调时，所有参数不能为空");
        }

        String plainText = DingTalkUtils.getDecryptMsg(signature, timeStamp, nonce, encrypt);
        logger.warn("明文: " + plainText);
        Map callbackMap = ToolsKit.jsonParseObject(plainText, Map.class);
        if(ToolsKit.isEmpty(callbackMap)) {
            throw new ServiceException("根据解密的字符串转换成Map时出错: Map为空");
        }
        String eventType = callbackMap.get("EventType") + "";
        switch (eventType) {
            case "user_modify_org " :
                logger.debug("通讯录用户更改");
                break;
            case "bpms_task_change" :
                logger.debug("审批任务开始/结束");
                break;
            case "bpms_instance_change" :
                logger.debug("审批实例开始/结束");
                break;
        }

        return "success";
    }
}
