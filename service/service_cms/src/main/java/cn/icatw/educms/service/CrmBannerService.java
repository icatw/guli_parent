package cn.icatw.educms.service;

import cn.icatw.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-10-10
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 获取所有横幅
     *
     * @return {@link List}<{@link CrmBanner}>
     */
    List<CrmBanner> selectAllBanner();

}
