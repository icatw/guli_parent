package cn.icatw.msmservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.msmservice.service.MsmService;
import cn.icatw.msmservice.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author icatw
 * @date 2022/10/11
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    SmsUtil smsUtil;

    @Override
    public boolean send(String phone, String code) {
        try {
            smsUtil.sendCode(phone, code);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "短信发送失败！");
        }
        return true;
    }
}
