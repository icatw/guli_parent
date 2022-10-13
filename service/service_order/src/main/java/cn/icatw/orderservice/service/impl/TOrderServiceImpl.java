package cn.icatw.orderservice.service.impl;

import cn.icatw.orderservice.entity.TOrder;
import cn.icatw.orderservice.mapper.TOrderMapper;
import cn.icatw.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
