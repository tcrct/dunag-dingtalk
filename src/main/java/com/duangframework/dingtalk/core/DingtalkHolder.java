package com.duangframework.dingtalk.core;

/**
 * 钉钉SDK主入口
 *
 * @author  laotang
 * @date 2019/5/9.
 */
public class DingtalkHolder {

    /**
     *  用户类
     */
    public static class USER extends UserUtils {
    }

    /**
     *  部门类
     */
    public static class DEPT extends DepartmentUtils {
    }

    /**
     *  角色类
     */
    public static class ROLE extends RoleUtils {
    }

    /**
     *  消息通知类
     */
    public static class MESSAGE extends MessageUtils {
    }

    /**
     *  公告类
     */
    public static class NOTICE extends NoticeUtils {
    }

    /**
     *  日志类
     */
    public static class LOG extends LogUtils {
    }

    /**
     *  审批类
     */
    public static class PROCESS extends ProcessUtils {
    }

    /**
     *  回调类
     */
    public static class CALLBACK extends CallbackUtils {
    }

}
