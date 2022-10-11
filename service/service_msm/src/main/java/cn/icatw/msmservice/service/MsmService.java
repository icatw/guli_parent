package cn.icatw.msmservice.service;

/**
 * @author icatw
 * @date 2022/10/11
 * @email 762188827@qq.com
 * @apiNote
 */
public interface MsmService {
    /**
     * 发送
     *
     * @param phone 电话
     * @param code 电话
     * @return boolean
     */
    boolean send(String phone, String code);
}
