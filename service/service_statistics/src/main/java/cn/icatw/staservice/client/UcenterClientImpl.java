package cn.icatw.staservice.client;

import cn.icatw.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author icatw
 * @date 2022/10/13
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public R countRegister(String day) {
        return R.error().message("查询当日注册用户失败！");
    }
}
