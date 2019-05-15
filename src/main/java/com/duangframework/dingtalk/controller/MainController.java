package com.duangframework.dingtalk.controller;

import com.duangframework.mvc.annotation.Controller;
import com.duangframework.mvc.annotation.Mapping;
import com.duangframework.mvc.core.BaseController;

@Controller
@Mapping(value = "/")
public class MainController extends BaseController {
    public void main() {
        System.out.println("main");
        returnText("success");
    }
}
