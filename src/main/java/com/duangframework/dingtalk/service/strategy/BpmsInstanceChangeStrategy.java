package com.duangframework.dingtalk.service.strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    }

}
