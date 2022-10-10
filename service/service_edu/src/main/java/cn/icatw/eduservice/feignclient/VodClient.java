package cn.icatw.eduservice.feignclient;

import cn.icatw.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 创建feignclient包
 *
 * @author 76218
 * @FeignClient注解用于指定从哪个服务中调用功能 ，名称与被调用的服务名保持一致。
 * @GetMapping注解用于对被调用的微服务进行地址映射。
 * @PathVariable注解一定要指定参数名称，否则出错
 * @Component注解防止，在其他位置注入CodClient时idea报错
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    /**
     * 删除视频
     *
     * @param id id
     * @return {@link R}
     */
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    /**
     * 删除批处理
     *
     * @param videoIdList 视频id列表
     * @return {@link R}
     */
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
