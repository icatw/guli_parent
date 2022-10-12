package cn.icatw.eduservice.feignclient;

import cn.icatw.eduservice.entity.UcenterMember;
import org.springframework.stereotype.Component;

/**
 * @author icatw
 * @date 2022/10/13
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
public class UcenterClientImpl implements UserClient {
    @Override
    public UcenterMember getMemberInfoById(String memberId) {
        return null;
    }
}
