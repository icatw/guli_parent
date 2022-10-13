package cn.icatw.orderservice.service;

import cn.icatw.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
public interface TOrderService extends IService<TOrder> {

    /**
     * 保存订单
     *
     * @param courseId           进程id
     * @param memberIdByJwtToken 成员由jwt id令牌
     * @return {@link String}
     */
    String saveOrder(String courseId, String memberIdByJwtToken);
}
