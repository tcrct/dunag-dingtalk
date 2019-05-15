package com.duangframework.dingtalk.controller;

import com.alibaba.fastjson.JSONObject;
import com.duangframework.db.enums.LevelEnums;
import com.duangframework.dingtalk.service.CallbackService;
import com.duangframework.kit.ToolsKit;
import com.duangframework.mvc.annotation.Controller;
import com.duangframework.mvc.annotation.Import;
import com.duangframework.mvc.annotation.Mapping;
import com.duangframework.mvc.core.BaseController;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;

/**
 *  企业通讯录回调地址实现
 * @author laotang
 * @date 2019-05-15
 */
@Controller
@Mapping(value = "/", desc = "回调管理", level = LevelEnums.DIR)
public class CallbackController extends BaseController {

    @Import
    private CallbackService callbackService;

    public void callback() {
        /**url中的签名**/
        String signature = getRequest().getParameter("signature");
        /**url中的时间戳**/
        String timeStamp = getRequest().getParameter("timestamp");
        /**url中的随机字符串**/
        String nonce = getRequest().getParameter("nonce");
        /**post数据包数据中的加密数据**/
        String encrypt = "";
        InputStream is = getInputStream();
        if(ToolsKit.isNotEmpty(is)) {
            try {
                String postString = IOUtils.toString(getInputStream());
                JSONObject jsonEncrypt = JSONObject.parseObject(postString);
                encrypt = jsonEncrypt.getString("encrypt");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        callbackService.callback(signature, timeStamp, nonce, encrypt);
    }
}
