package cn.icatw.orderservice.feign;

import cn.icatw.commonutils.ordervo.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author icatw
 * @date 2022/10/13
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
@FeignClient(value = "service-edu")
public interface CourseClient {
    /**
     * 获取课程信息秩序
     *
     * @param id id
     * @return {@link CourseWebVoOrder}
     */
    @ApiOperation(value = "根据课程id查询课程信息，供订单服务远程调用")
    @GetMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id);
}
