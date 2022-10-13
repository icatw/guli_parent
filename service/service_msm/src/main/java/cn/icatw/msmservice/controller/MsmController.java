package cn.icatw.msmservice.controller;

import cn.icatw.commonutils.R;
import cn.icatw.msmservice.service.MsmService;
import cn.icatw.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController

@Api(tags = "短信服务")
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    @ApiOperation(value = "发送短信")
    public R sendMsm(@PathVariable String phone) {
        //先从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //如果redis获取不到，进行阿里云发送

        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        //HashMap<Object, Object> param = new HashMap<>();
        //param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(phone, code);
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }


    }
}

