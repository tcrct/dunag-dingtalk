package com.duangframework.dingtalk.service.strategy;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.duangframework.dingtalk.sdk.core.DingtalkHolder;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.kit.ToolsKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 审批实例开始/结束
 *
 * @author  laotang
 * @date 2019/5/16
 */
public class BpmsInstanceChangeStrategy implements IStrategy {

    private final static Logger logger = LoggerFactory.getLogger(BpmsInstanceChangeStrategy.class);

    @Override
    public void handle(String plainText) {
        logger.warn("审批实例开始/结束: " + plainText);
        Map<String, String> resultMap = ToolsKit.jsonParseObject(plainText, Map.class);
        String processInstanceId = resultMap.get("processInstanceId");
        if(ToolsKit.isEmpty(processInstanceId)) {
            logger.warn("processInstanceId is null");
        }
        DingtalkResponse<OapiProcessinstanceGetResponse> dingtalkResponse = DingtalkHolder.PROCESS.getSingleApproval(processInstanceId);
        OapiProcessinstanceGetResponse response = dingtalkResponse.getResult();
        if(response.isSuccess()) {
            OapiProcessinstanceGetResponse.ProcessInstanceTopVo  vo = response.getProcessInstance();
            //status: COMPLETED                 result: refuse  审批流程走完且是拒绝
//        status: COMPLETED                 result: finish   审批流程走完且是同意
            System.out.println("status: " + vo.getStatus()+"                 result: "+vo.getResult());
        }
    }

}
