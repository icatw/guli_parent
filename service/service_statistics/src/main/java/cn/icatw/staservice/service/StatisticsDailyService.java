package cn.icatw.staservice.service;

import cn.icatw.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 获取显示数据
     *
     * @param type  类型
     * @param begin
     * @param end   结束
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getShowData(String type, String begin, String end);
}
