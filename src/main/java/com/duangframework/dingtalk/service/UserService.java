package com.duangframework.dingtalk.service;

import com.dingtalk.api.response.OapiUserCreateResponse;
import com.duangframework.dingtalk.core.DingtalkHolder;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import com.duangframework.dingtalk.dto.UserDto;
import com.duangframework.dingtalk.service.handle.IDateHandle;
import com.duangframework.exception.ServiceException;
import com.duangframework.kit.ToolsKit;
import com.duangframework.mvc.annotation.Service;
import com.duangframework.vtor.core.VtorFactory;

/**
 * 用户管理业务层
 * @author laotang
 * @date 2019-05-14
 */
@Service
public class UserService extends AbstractDateHandle {

    public static final String CREATE_FLAG_FIELD = UserService.class.getName().toLowerCase() +".create" ;

    public boolean create(UserDto userDto) {
        try {
            VtorFactory.validator(userDto);
            DingtalkResponse<OapiUserCreateResponse> response = DingtalkHolder.USER.createUser(userDto);
            if(ToolsKit.isEmpty(response)){
                return false;
            }
            // 保存到业务数据库
            handleData(CREATE_FLAG_FIELD, "User", response);
            return response.getResult().isSuccess();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
