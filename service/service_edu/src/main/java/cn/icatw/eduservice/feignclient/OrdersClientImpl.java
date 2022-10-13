package cn.icatw.eduservice.feignclient;

import org.springframework.stereotype.Component;

/**
 * @author icatw
 * @date 2022/10/13
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
public class OrdersClientImpl implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
