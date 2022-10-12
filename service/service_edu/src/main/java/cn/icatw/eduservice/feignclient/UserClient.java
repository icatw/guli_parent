package cn.icatw.eduservice.feignclient;

import cn.icatw.eduservice.entity.UcenterMember;
import io.swagger.annotations.ApiOperation;
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
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)
@Component
public interface UserClient {
    @ApiOperation(value = "评论前先登录，查询用户信息")
    @PostMapping("/ucenterservice/apimember/getMemberInfoById/{memberId}")
    public UcenterMember getMemberInfoById(@PathVariable String memberId);
}
