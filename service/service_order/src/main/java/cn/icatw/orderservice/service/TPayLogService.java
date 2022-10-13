package cn.icatw.orderservice.service;

import cn.icatw.orderservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
public interface TPayLogService extends IService<TPayLog> {

    /**
     * 创建二维码
     *
     * @param orderNo 订单没有
     * @return {@link Map}
     */
    Map createNative(String orderNo);

    /**
     * 查询支付状态
     *
     * @param orderNo 订单没有
     * @return {@link Map}<{@link String}, {@link String}>
     */
    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
