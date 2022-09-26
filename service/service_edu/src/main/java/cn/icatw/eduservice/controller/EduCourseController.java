package cn.icatw.eduservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.vo.CourseInfoVo;
import cn.icatw.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息
    @ApiOperation(value = "新增课程")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }
}

