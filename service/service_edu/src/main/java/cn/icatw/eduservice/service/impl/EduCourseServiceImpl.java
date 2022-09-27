package cn.icatw.eduservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.EduCourseDescription;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.mapper.EduCourseMapper;
import cn.icatw.eduservice.service.EduCourseDescriptionService;
import cn.icatw.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        //将courseInfoVo对象转化成eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
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
}
