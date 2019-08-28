package com.duangframework.dingtalk.sdk.core;

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

    /**
     *  通讯录权限
     */
    public static class ABPS extends AddressBookPermissionScopeUtils {
    }

    /**
     *  考勤类
     */
    public static class WORKATTEND extends CheckOnWorkAttendanceUtils {
    }

    /**
     *  外部联系人类
     */
    public static class EXTCONTACT extends ExternalContactManagementUtils {
    }

    /**
     *  群消息
     */
    public static class GROUPMESSAGE extends SendGroupMessagesUtils {
    }

    /**
     *  群消息
     */
    public static class MESSAGE extends SendOrdinaryMessageUtils {
    }

    /**
     *  公告类
     */
    public static class NOTICE extends WordNoticeMessageUtils {
    }

    /**
     * SNS
     */
    public static class SNS extends  SnsUtils {
    }
}
