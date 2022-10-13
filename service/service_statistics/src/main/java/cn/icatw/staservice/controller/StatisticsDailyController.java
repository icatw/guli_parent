package cn.icatw.staservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/staservice/sta")
@Api(tags = "统计管理")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staService;

    //统计某天的注册人数(远程调用)并加到统计表
    @ApiOperation(value = "统计某天的注册人数(远程调用)并加到统计表")
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return R.ok();
    }

    //图表显示,返回两部分数据:日期的json数组(横坐标)和数量的json数组(纵坐标)
    @ApiOperation(value = "图表显示,返回两部分数据:日期的json数组(横坐标)和数量的json数组(纵坐标)")
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end) {
        Map<String, Object> map = staService.getShowData(type, begin, end);
        return R.ok().data(map);
    }

}

