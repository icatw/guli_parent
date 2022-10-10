package cn.icatw.eduservice.service.impl;

import cn.icatw.eduservice.entity.EduVideo;
import cn.icatw.eduservice.mapper.EduVideoMapper;
import cn.icatw.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("course_id", courseId);
        baseMapper.delete(QueryWrapper);
    }

}
