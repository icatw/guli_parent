package cn.icatw.educenter.service;

import cn.icatw.educenter.entity.UcenterMember;
import cn.icatw.educenter.entity.vo.LoginVo;
import cn.icatw.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-10-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登录
     *
     * @param loginVo 登录签证官
     * @return {@link String}
     */
    String login(LoginVo loginVo);

    /**
     * 注册
     *
     * @param registerVo 注册签证官
     */
    void register(RegisterVo registerVo);

    /**
     * @param openid
     * @return {@link UcenterMember}
     */
    UcenterMember getOpenIdMember(String openid);
}
