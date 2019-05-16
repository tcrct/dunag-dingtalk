package com.duangframework.dingtalk.controller;

import com.duangframework.db.enums.LevelEnums;
import com.duangframework.dingtalk.sdk.dto.UserDto;
import com.duangframework.dingtalk.service.UserService;
import com.duangframework.mvc.annotation.Bean;
import com.duangframework.mvc.annotation.Controller;
import com.duangframework.mvc.annotation.Import;
import com.duangframework.mvc.annotation.Mapping;
import com.duangframework.mvc.core.BaseController;

@Controller
@Mapping(value = "/user", desc = "用户管理", level = LevelEnums.DIR)
public class UserController extends BaseController {

    @Import
    private UserService userService;

    @Mapping(value = "/create")
    public void create(@Bean UserDto userDto) {
        try {
            userService.create(userDto);
        } catch (Exception e) {

        }

    }

}
