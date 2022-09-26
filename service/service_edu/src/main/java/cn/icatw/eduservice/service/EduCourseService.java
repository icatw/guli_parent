package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-09-26
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程信息
     *
     * @param courseInfoVo 课程信息签证官
     * @return {@link String}
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
