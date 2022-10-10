package cn.icatw.eduservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.entity.vo.CoursePublishVo;
import cn.icatw.eduservice.entity.vo.CourseQueryVo;
import cn.icatw.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-09-26
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     * 添加课程信息
     *
     * @param courseInfoVo 课程信息签证官
     * @return {@link R}
     */
    @ApiOperation(value = "新增课程")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //根据课程id查询课程基本信息

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @ApiOperation(value = "更新课程")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @ApiOperation(value = "根据课程id查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        boolean b = courseService.updateById(eduCourse);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页条件查询")
    @PostMapping("{page}/{limit}")
    public R getCourseList(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseQueryVo courseQuery) {
        Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        courseService.pageQuery(eduCoursePage, courseQuery);
        List<EduCourse> list = eduCoursePage.getRecords();
        long total = eduCoursePage.getTotal();
        return R.ok().data("list", list).data("rows", total);
    }

    //删除课程
    @ApiOperation(value = "根据ID删除课程")
    @PostMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        boolean remove = courseService.removeCourse(courseId);
        if (remove) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

