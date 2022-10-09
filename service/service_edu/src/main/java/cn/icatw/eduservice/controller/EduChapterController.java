package cn.icatw.eduservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.chapter.ChapterVo;
import cn.icatw.eduservice.service.EduChapterService;
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
 * @since 2022-09-27
 */
@Api(tags = "课程章节管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("getChapterVideo/{courseId}")
    public R nestedListByCourseId(@PathVariable String courseId) {
        List<ChapterVo> chapterVos = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", chapterVos);
    }
}

