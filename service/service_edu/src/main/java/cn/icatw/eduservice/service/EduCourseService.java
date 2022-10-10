package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.entity.vo.CoursePublishVo;
import cn.icatw.eduservice.entity.vo.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 获取课程信息
     *
     * @param courseId 进程id
     * @return {@link CourseInfoVo}
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 更新课程信息
     *
     * @param courseInfoVo 课程信息签证官
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 发布课程信息
     *
     * @param id id
     * @return {@link CoursePublishVo}
     */
    CoursePublishVo publishCourseInfo(String id);

    void pageQuery(Page<EduCourse> eduCoursePage, CourseQueryVo courseQuery);

    boolean removeCourse(String courseId);
}
