package cn.icatw.eduservice.controller.front;

import cn.icatw.commonutils.R;
import cn.icatw.commonutils.ordervo.CourseWebVoOrder;
import cn.icatw.eduservice.entity.EduCourse;
import cn.icatw.eduservice.entity.chapter.ChapterVo;
import cn.icatw.eduservice.entity.frontvo.CourseFrontVo;
import cn.icatw.eduservice.entity.frontvo.CourseWebVo;
import cn.icatw.eduservice.feignclient.OrdersClient;
import cn.icatw.eduservice.service.EduChapterService;
import cn.icatw.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@Api(tags = "前台课程模块")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;
    @Resource
    private OrdersClient ordersClient;
    @Autowired
    private EduChapterService chapterService;

    //1 条件查询带分页查询课程
    @ApiOperation(value = "条件查询带分页查询课程")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    ////2 课程详情的方法
    //@GetMapping("getFrontCourseInfo/{courseId}")
    //public ResultVo getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
    //    //根据课程id，编写sql语句查询课程信息
    //    CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
    //    //根据课程id查询章节和小节
    //    List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
    //    //根据课程id和用户id查询当前课程是否已经支付过了
    //    boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
    //    return ResultVo.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy", buyCourse);
    //}

    //2 课程详情的方法
    @ApiOperation(value = "课程详情的方法")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy", buyCourse);
    }

    @ApiOperation(value = "根据课程id查询课程信息，供订单服务远程调用")
    @GetMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}












