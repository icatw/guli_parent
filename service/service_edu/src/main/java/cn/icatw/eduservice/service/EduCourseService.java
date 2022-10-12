package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.frontvo.CourseFrontVo;
import cn.icatw.eduservice.entity.frontvo.CourseWebVo;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.entity.vo.CoursePublishVo;
import cn.icatw.eduservice.entity.vo.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 页面查询
     *
     * @param eduCoursePage edu课程页面
     * @param courseQuery   课程查询
     */
    void pageQuery(Page<EduCourse> eduCoursePage, CourseQueryVo courseQuery);

    /**
     * 删除课程
     *
     * @param courseId 进程id
     * @return boolean
     */
    boolean removeCourse(String courseId);

    /**
     * @param pageCourse    页面课程
     * @param courseFrontVo 课程前签证官
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    /**
     * 得到基层信息
     *
     * @param courseId 进程id
     * @return {@link CourseWebVo}
     */
    CourseWebVo getBaseCourseInfo(String courseId);

    /**
     * 更新课程浏览数
     *
     * @param id
     */
    void updatePageViewCount(String id);
}
