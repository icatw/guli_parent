package cn.icatw.educenter.controller;


import cn.icatw.commonutils.R;
import cn.icatw.commonutils.ordervo.UcenterMemberOrder;
import cn.icatw.educenter.entity.UcenterMember;
import cn.icatw.educenter.entity.vo.LoginVo;
import cn.icatw.educenter.entity.vo.RegisterVo;
import cn.icatw.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-10-11
 */
@RestController
@Api(tags = "会员模块")
@CrossOrigin
@RequestMapping("/ucenterservice/apimember")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    @ApiOperation(value = "登陆")
    public R loginUser(@RequestBody LoginVo member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    @ApiOperation(value = "根据token获取用户信息")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }

    //2.评论前先登录，查询用户信息
    @ApiOperation(value = "评论前先登录，查询用户信息")
    @PostMapping("/getMemberInfoById/{memberId}")
    public UcenterMember getMemberInfoById(@PathVariable String memberId) {
        return memberService.getById(memberId);
    }

    //根据用户id获取用户信息
    @ApiOperation(value = "根据用户id获取用户信息，供订单服务远程调用")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

}

