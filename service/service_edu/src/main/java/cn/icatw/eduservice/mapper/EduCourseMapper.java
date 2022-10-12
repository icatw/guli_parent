package cn.icatw.eduservice.mapper;

import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.frontvo.CourseWebVo;
import cn.icatw.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author icatw
 * @since 2022-09-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 获得发布课程信息
     *
     * @param id id
     * @return {@link CoursePublishVo}
     */
    CoursePublishVo getPublishCourseInfo(String id);

    /**
     * 得到基层信息
     *
     * @param courseId 进程id
     * @return {@link CourseWebVo}
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
