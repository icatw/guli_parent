package cn.icatw.msmservice.utils;

import cn.icatw.baseservice.exception.GuliException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author icatw
 * @date 2022/10/11
 * @email 762188827@qq.com
 * @apiNote
 */
@Slf4j
@Service
public class SmsUtil {
    @Autowired
    ConstantPropertiesUtils constantPropertiesUtils;

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public void sendCode(String phoneNumber, String code) throws Exception {
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String secrect = ConstantPropertiesUtils.SECRECT;
        String signname = ConstantPropertiesUtils.SIGNNAME;
        String templateCode = ConstantPropertiesUtils.TEMPLATE_CODE;
        com.aliyun.dysmsapi20170525.Client client = createClient(accessKeyId, secrect);
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName(signname)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(JSONObject.toJSONString(map));
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            SendSmsResponseBody body = sendSmsResponse.getBody();
            log.info("---------阿里短信api返回结果为-------");
            log.info("code:" + body.getCode());
            log.info("msg:" + body.getMessage());
            log.info("bizId:" + body.getBizId());
            if (!body.getCode().equals("OK")) {
                throw new GuliException(20001, "短信发送失败！");
            }
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            log.error(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
            log.error(error.message);
        }
    }

}
