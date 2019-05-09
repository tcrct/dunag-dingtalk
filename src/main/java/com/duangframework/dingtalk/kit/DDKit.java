package com.duangframework.dingtalk.kit;

import com.duangframework.dingtalk.kit.department.DepartmentUtils;
import com.duangframework.kit.ToolsKit;

/**
 * 钉钉工具类
 * @author  laotang
 * @date 2019/5/9.
 */
public class DDKit {

    private static DepartmentUtils departmentUtils = null;

    public static DepartmentUtils department() {
        if(ToolsKit.isEmpty(departmentUtils)) {
            departmentUtils = DepartmentUtils.getInstance();
        }
        return departmentUtils;
    }

}
