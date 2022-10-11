package cn.icatw.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件工具类
 *
 * @author 76218
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.secret}")
    private String secret;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;
    @Value("${aliyun.sms.signName}")
    private String signName;

    public static String ACCESS_KEY_ID;
    public static String SECRECT;
    public static String TEMPLATE_CODE;
    public static String SIGNNAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        SECRECT = secret;
        TEMPLATE_CODE = templateCode;
        SIGNNAME = signName;
    }
}
