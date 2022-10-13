package cn.icatw.staservice.service;

import cn.icatw.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 注册数
     *
     * @param day 一天
     */
    void registerCount(String day);
}
