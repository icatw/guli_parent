package cn.icatw.staservice.client;

import cn.icatw.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某天注册人数
    @GetMapping("ucenterservice/apimember/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
