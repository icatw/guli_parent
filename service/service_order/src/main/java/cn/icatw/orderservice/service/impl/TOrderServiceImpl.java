package cn.icatw.orderservice.service.impl;

import cn.icatw.commonutils.ordervo.CourseWebVoOrder;
import cn.icatw.commonutils.ordervo.UcenterMemberOrder;
import cn.icatw.orderservice.entity.TOrder;
import cn.icatw.orderservice.feign.CourseClient;
import cn.icatw.orderservice.feign.UserClient;
import cn.icatw.orderservice.mapper.TOrderMapper;
import cn.icatw.orderservice.service.TOrderService;
import cn.icatw.orderservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author icatw
 * @date 2022/10/13
 * @since 2022-10-13
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    CourseClient courseClient;
    @Autowired
    UserClient userClient;

    @Override
    public String saveOrder(String courseId, String memberId) {
        CourseWebVoOrder courseInfoOrder = courseClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfoOrder = userClient.getUserInfoOrder(memberId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
