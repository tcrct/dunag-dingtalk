package com.duangframework.dingtalk.service.strategy;

/**
 * 回调事件枚举
 *
 * @author  laotang
 * @date 2019/5/16
 */
public enum EventTypeEnum {

    USER_MODIFY_ORG("user_modify_org", "通讯录用户更改", new UserModifyOrgStrategy()),
//    BPMS_TASK_CHANGE("bpms_task_change", "审批任务开始/结束", new BpmsTaskChangeStrategy()),
    BPMS_INSTANCE_CHANGE("bpms_instance_change", "审批实例开始/结束", new BpmsInstanceChangeStrategy()),

    ;

    /**回调事件类型*/
    private String eventType;
    /**回调事件说明*/
    private String eventDesc;
    /**对应的处理策略类*/
    private IStrategy strategy;

    EventTypeEnum(String eventType, String eventDesc, IStrategy strategy) {
        this.eventType = eventType;
        this.eventDesc = eventDesc;
        this.strategy = strategy;
    }
    public String getType() {
        return eventType;
    }
    public String getDesc() {
        return eventDesc;
    }
    public IStrategy getStrategy() {
        return strategy;
    }
}
