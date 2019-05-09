package com.duangframework.dingtalk.kit.department;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.duangframework.dingtalk.utils.AuthUtils;
import com.duangframework.kit.ToolsKit;
import com.taobao.api.TaobaoResponse;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Created by laotang on 2019/5/9.
 */
public class DepartmentUtils {

//    private static final Logger logger = LoggerFactory.getLogger(DepartmentUtils.class);

    private static Lock lock = new ReentrantLock();
    private static DepartmentUtils INSTANCE;


    public static DepartmentUtils getInstance() {
        try {
            lock.lock();
            if (ToolsKit.isEmpty(INSTANCE)) {
                INSTANCE = new DepartmentUtils();
            }
        } catch (Exception e) {
//            logger.warn("DepartmentUtils getInstance is fail: " + e.getMessage(), e);
        }finally {
            lock.unlock();
        }
        return INSTANCE;
    }

    private DepartmentUtils() {
    }

    /**
     *   获取子部门ID列表
     * */
    private static final String DEPARTMENT_IDS_API = "https://oapi.dingtalk.com/department/list_ids";
    public OapiDepartmentListIdsResponse getDepartmentListIds(String departId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DEPARTMENT_IDS_API);
            OapiDepartmentListIdsRequest request = new OapiDepartmentListIdsRequest();
            request.setId(departId);
            request.setHttpMethod("GET");
            return client.execute(request, AuthUtils.getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
