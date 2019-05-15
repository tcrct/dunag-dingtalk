package com.duangframework.dingtalk.service.strategy;

import com.duangframework.dingtalk.service.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 通讯录用户更改
 *
 * @author  laotang
 * @date 2019/5/16
 */
public class UserModifyOrgStrategy implements IStrategy {

    private final static Logger logger = LoggerFactory.getLogger(UserModifyOrgStrategy.class);

    @Override
    public void handle(String plainText) {
        logger.warn("通讯录用户更改: " + plainText);
    }

}
