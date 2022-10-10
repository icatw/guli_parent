package cn.icatw.vodservice.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author icatw
 * @date 2022/10/10
 * @email 762188827@qq.com
 * @apiNote
 */
@ConfigurationProperties(prefix = "aliyun.vod.file")
@Component
@Data
public class AliConstant {
    private String keyId;
    private String keySecret;
}
