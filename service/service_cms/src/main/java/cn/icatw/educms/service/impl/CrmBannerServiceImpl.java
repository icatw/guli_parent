package cn.icatw.educms.service.impl;

import cn.icatw.educms.entity.CrmBanner;
import cn.icatw.educms.mapper.CrmBannerMapper;
import cn.icatw.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-10-10
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(value = "banner", key = "'selectIndexList'")
    public List<CrmBanner> selectAllBanner() {
        //根据id进行降序排序，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        return baseMapper.selectList(queryWrapper);
    }

}
