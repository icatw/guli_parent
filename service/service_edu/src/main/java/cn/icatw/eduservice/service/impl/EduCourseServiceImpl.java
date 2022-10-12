package cn.icatw.eduservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.EduCourseDescription;
import cn.icatw.eduservice.entity.frontvo.CourseFrontVo;
import cn.icatw.eduservice.entity.frontvo.CourseWebVo;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.entity.vo.CoursePublishVo;
import cn.icatw.eduservice.entity.vo.CourseQueryVo;
import cn.icatw.eduservice.mapper.EduCourseMapper;
import cn.icatw.eduservice.service.EduChapterService;
import cn.icatw.eduservice.service.EduCourseDescriptionService;
import cn.icatw.eduservice.service.EduCourseService;
import cn.icatw.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-09-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService courseDescriptionService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;

    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        //将courseInfoVo对象转化成eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        String[] subjectIds = courseInfoVo.getSubjectIds();
        //饿了么级联，数组传值，第一个为父id，第二个为子id
        eduCourse.setSubjectParentId(subjectIds[0]);
        eduCourse.setSubjectId(subjectIds[1]);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }
        //获取添加之后的课程id
        String cid = eduCourse.getId();

        //向课程简介表添加课程介绍
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        //设置描述id就是课程id
        courseDescription.setId(cid);
        //courseDescription.setDescription(courseInfoVo.getDescription());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if (!resultDescription) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        String[] strings = new String[2];
        strings[0] = eduCourse.getSubjectParentId();
        strings[1] = eduCourse.getSubjectId();
        courseInfoVo.setSubjectIds(strings);
        //查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        if (courseDescription != null) {
            BeanUtils.copyProperties(courseDescription, courseInfoVo);
        }
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        String[] subjectIds = courseInfoVo.getSubjectIds();
        eduCourse.setSubjectParentId(subjectIds[0]);
        eduCourse.setSubjectId(subjectIds[1]);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new GuliException(20001, "修改课程信息失败");

        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public void pageQuery(Page<EduCourse> eduCoursePage, CourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null) {
            this.page(eduCoursePage, queryWrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String status = courseQuery.getStatus();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        this.page(eduCoursePage, queryWrapper);
    }

    @Override
    public boolean removeCourse(String courseId) {

        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        courseDescriptionService.removeById(courseId);
        //根据课程id删除课程本身
        int i = baseMapper.deleteById(courseId);
        if (i == 0) {
            throw new GuliException(20001, "删除失败");
        }
        return true;
    }

    //1 条件查询带分页查询课程
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        this.updatePageViewCount(courseId);
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }


}
