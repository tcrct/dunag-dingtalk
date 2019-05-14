package com.duangframework.dingtalk.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.duangframework.dingtalk.dto.DingtalkResponse;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author laotang
 * @date 2019/5/9.
 */
public class AuthUtils {

    private final static Logger logger = LoggerFactory.getLogger(AuthUtils.class);

    private static final Map<String, DingtalkResponse> TOKEN_RESPONSE_MAP = new HashMap<>();

    private static final String GET_TOKEN_API = "https://oapi.dingtalk.com/gettoken";
    public static String getAccessToken() throws Exception {
        long curTime = System.currentTimeMillis();
        DingtalkResponse<OapiGettokenResponse> response = TOKEN_RESPONSE_MAP.get(DingTalkConfig.APPKEY_FIELD);
        boolean isExpires = true;
        OapiGettokenResponse ogr = null;
        if(null != response) {
            ogr = response.getResult();
            // 当前时间-开始时间大于等于有效时间前10分钟，则视为过期，即在正常的有效时间(2小时)提前10分钟
            isExpires = (curTime - response.getBeginTime()) >= (ogr.getExpiresIn()-600) * 1000;
        }
        if (null == response || isExpires) {
            DingTalkConfig dingTalkConfig = DangtalkUtils.getDingtalkConfig();
            try {
                DefaultDingTalkClient client = new DefaultDingTalkClient(GET_TOKEN_API);
                OapiGettokenRequest request = new OapiGettokenRequest();
                request.setAppkey(dingTalkConfig.getAppKey());
                request.setAppsecret(dingTalkConfig.getAppSecret());
                request.setHttpMethod(HttpMethod.GET.name());
                ogr = client.execute(request);
                response = new DingtalkResponse(ogr);
                response.setBeginTime(curTime);
                // save to cache
                TOKEN_RESPONSE_MAP.put(dingTalkConfig.getAppKey(), response);
            } catch (Exception e) {
                logger.warn("getAccessToken fail: " + e.getMessage(), e);
            }
        }
        return null == ogr ? "" : ogr.getAccessToken();
    }

}
