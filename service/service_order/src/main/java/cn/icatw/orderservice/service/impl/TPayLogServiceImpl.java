package cn.icatw.orderservice.service.impl;

import cn.icatw.orderservice.entity.TPayLog;
import cn.icatw.orderservice.mapper.TPayLogMapper;
import cn.icatw.orderservice.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

}
