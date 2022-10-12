package cn.icatw.educenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:application.properties")
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${wx.open.app_id}")
    private String appId;
    @Value("${wx.open.app_secret}")
    private String appSecret;
    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Value("${gitee.client_id}")
    private String clientId;
    @Value("${gitee.client_secret}")
    private String clientSecret;
    @Value("${gitee.redirect_uri}")
    private String redirectUri;

    public static String GITEE_CLIENT_ID;
    public static String GITEE_CLIENT_SECRET;
    public static String GITEE_REDIRECT_URI;


    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;

        GITEE_CLIENT_ID = clientId;
        GITEE_CLIENT_SECRET = clientSecret;
        GITEE_REDIRECT_URI = redirectUri;
    }
}
