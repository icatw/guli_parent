package cn.icatw.educenter.controller;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.educenter.entity.GiteeUser;
import cn.icatw.educenter.entity.UcenterMember;
import cn.icatw.educenter.service.UcenterMemberService;
import cn.icatw.educenter.utils.ConstantPropertiesUtil;
import cn.icatw.educenter.utils.ProviderToken;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.JwtUtils;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/12
 * @email 762188827@qq.com
 * @apiNote
 */
@Controller

@RequestMapping("/api/ucenter/gitee")
@Slf4j
public class GiteeApiController {
    @Autowired
    UcenterMemberService memberService;

    @GetMapping("login")
    public String login() {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://gitee.com/oauth/authorize" + "?client_id=%s" + "&redirect_uri=%s" + "&response_type=code";
        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.GITEE_REDIRECT_URI; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001, e.getMessage());
        }
        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "imhelen";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        System.out.println("state = " + state);
        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(baseUrl, ConstantPropertiesUtil.GITEE_CLIENT_ID, redirectUrl, state);
        return "redirect:" + qrcodeUrl;
    }


    @GetMapping("callback")
    public String callback(String code, String state, HttpSession session) {
        //1. 创建http请求，构建请求体和请求url等，并向gitee发起请求
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        ProviderToken providerToken = new ProviderToken();
        providerToken.setCode(code);
        providerToken.setClientId(ConstantPropertiesUtil.GITEE_CLIENT_ID);
        providerToken.setRedirectUri(ConstantPropertiesUtil.GITEE_REDIRECT_URI);
        providerToken.setClientSecret(ConstantPropertiesUtil.GITEE_CLIENT_SECRET);
        providerToken.setState(state);
        RequestBody body = RequestBody.create(JSON.toJSONString(providerToken), mediaType);
        String url = "https://gitee.com/oauth/token?grant_type=authorization_code&code=" + providerToken.getCode()
                + "&client_id=" + providerToken.getClientId()
                + "&redirect_uri=" + providerToken.getRedirectUri()
                + "&client_secret=" + providerToken.getClientSecret();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        //2. 获取gitee对应的响应消息，根据消息解析出用户的 access token
        String jwtToken = null;
        try (Response response = client.newCall(request).execute()) {
            String tokenStr = Objects.requireNonNull(response.body()).string();
            String accessToken = tokenStr.split(",")[0].split(":")[1];
            accessToken = accessToken.substring(1, accessToken.length() - 1);
            System.out.println("accessToken = " + accessToken);
            OkHttpClient userClient = new OkHttpClient();
            Request userRequest = new Request.Builder()
                    .url("https://gitee.com/api/v5/user?access_token=" + accessToken).build();
            //2. 获取gitee传回来的响应消息，根据消息解析出用户消息
            Response response2 = userClient.newCall(userRequest).execute();
            //String string = response.body().string();
            String giteeUserStr = Objects.requireNonNull(response2.body()).string();
            System.out.println("giteeUserStr:" + giteeUserStr);
            GiteeUser giteeUser = JSON.parseObject(giteeUserStr, GiteeUser.class);
            String openId = giteeUser.getId();
            UcenterMember member = memberService.getOpenIdMember(openId);
            if (member==null){
                member  = new UcenterMember();
                member.setAvatar(giteeUser.getAvatarUrl());
                member.setNickname(giteeUser.getName());
                member.setOpenid(giteeUser.getId());
                memberService.save(member);
            }
            jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token=" + jwtToken;
        } catch (Exception e) {
            e.getStackTrace();
            throw new GuliException(20001, "Gitee登陆失败！");
        }
    }
    //}
}
