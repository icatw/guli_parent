package cn.icatw.eduservice.feignclient;

import cn.icatw.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 视频点播文件服务熔断客户端
 *
 * @author icatw
 * @date 2022/10/10
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
