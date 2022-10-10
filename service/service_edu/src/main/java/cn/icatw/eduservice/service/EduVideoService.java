package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 删除视频课程id
     *
     * @param courseId 进程id
     */
    void removeVideoByCourseId(String courseId);

}
