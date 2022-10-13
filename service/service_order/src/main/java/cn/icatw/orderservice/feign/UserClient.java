package cn.icatw.orderservice.feign;

import cn.icatw.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author icatw
 * @date 2022/10/13
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
@FeignClient("service-ucenter")
public interface UserClient {
    /**
     * 得到用户信息订单
     *
     * @param id id
     * @return {@link UcenterMemberOrder}
     */
    @PostMapping("/ucenterservice/apimember/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id);
}
