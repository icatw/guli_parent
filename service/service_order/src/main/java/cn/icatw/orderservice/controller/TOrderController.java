package cn.icatw.orderservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.orderservice.entity.TOrder;
import cn.icatw.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/orderservice/order")
@Api(tags = "订单服务")

public class TOrderController {
    @Autowired
    private TOrderService orderService;

    //根据课程id和用户id创建订单，返回订单id
    @ApiOperation(value = "根据课程id和用户id创建订单，返回订单id")
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId,
                JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId", orderId);
    }

    @GetMapping("getOrder/{orderId}")
    @ApiOperation(value = "根据订单号获取订单详情")
    public R get(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        //已经支付
        return count > 0;
    }

}

