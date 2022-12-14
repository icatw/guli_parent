package cn.icatw.eduservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-order", fallback = OrdersClientImpl.class)
public interface OrdersClient {

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("/orderservice/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}

