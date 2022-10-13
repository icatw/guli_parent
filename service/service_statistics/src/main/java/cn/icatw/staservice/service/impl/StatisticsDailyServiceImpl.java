package cn.icatw.staservice.service.impl;

import cn.icatw.commonutils.R;
import cn.icatw.staservice.client.UcenterClient;
import cn.icatw.staservice.entity.StatisticsDaily;
import cn.icatw.staservice.mapper.StatisticsDailyMapper;
import cn.icatw.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    //统计某天的注册人数(远程调用)并加到统计表
    @Override
    public void registerCount(String day) {
        //插入新数据之前,先删掉表中与即将插入的数据日期相同的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);

        //远程调用,得到某天的注册人数
        R registerR = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");

        //把获取到的数据添加到统计表
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister); //注册人数
        sta.setDateCalculated(day); //统计的哪天的数据
        //统计表中的其它数据我们这里模拟生成随机数,后期自行完善
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100, 200));
        sta.setCourseNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(sta);
    }

}
