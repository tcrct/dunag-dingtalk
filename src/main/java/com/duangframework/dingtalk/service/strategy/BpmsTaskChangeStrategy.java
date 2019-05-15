package com.duangframework.dingtalk.service.strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 审批任务开始/结束
 *
 * @author  laotang
 * @date 2019/5/16
 */
public class BpmsTaskChangeStrategy implements IStrategy {

    private final static Logger logger = LoggerFactory.getLogger(BpmsTaskChangeStrategy.class);

    @Override
    public void handle(String plainText) {
        logger.warn("审批任务开始/结束: " + plainText);
    }

}
