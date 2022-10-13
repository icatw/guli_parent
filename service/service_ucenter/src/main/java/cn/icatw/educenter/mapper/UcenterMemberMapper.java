package cn.icatw.educenter.mapper;

import cn.icatw.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author icatw
 * @since 2022-10-11
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    /**
     * 统计日注册人数
     *
     * @param day 一天
     * @return {@link Integer}
     */
    Integer countRegisterDay(String day);
}
