package cn.icatw.eduservice.controller.front;

import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.EduTeacher;
import cn.icatw.eduservice.service.EduCourseService;
import cn.icatw.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@Api(tags = "前台首页课程和讲师")
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查看前8条热门课程，查询前4条名师
    @GetMapping("index")
    @ApiOperation(value = "查看前8条热门课程，查询前4条名师")
    public R index() {
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<EduCourse>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);

        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("id");
        eduTeacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(eduTeacherQueryWrapper);

        return R.ok().data("eduList", courseList).data("teacherList", teacherList);
    }
}
