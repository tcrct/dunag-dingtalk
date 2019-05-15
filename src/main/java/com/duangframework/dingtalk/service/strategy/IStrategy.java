package com.duangframework.dingtalk.service.strategy;

/**
 * 策略接口
 * @author  laotang
 * @date 2019/5/16
 */
public interface IStrategy {

    /**
     * 处理方法
     * @param plainText     解密后的明文字符串
     * @throws Exception
     */
    void handle(String plainText) throws Exception;

}
