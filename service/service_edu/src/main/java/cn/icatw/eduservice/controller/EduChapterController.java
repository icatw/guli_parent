package cn.icatw.eduservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.EduChapter;
import cn.icatw.eduservice.entity.chapter.ChapterVo;
import cn.icatw.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 //跨域
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

    //添加章节
    @ApiOperation(value = "新增章节")
    @PostMapping("addChapter")
    @ApiParam(name = "eduChapter", value = "章节对象", required = true)
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @ApiOperation(value = "根据ID查询章节")
    @ApiParam(name = "chapterId", value = "章节ID", required = true)
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", eduChapter);
    }

    //修改章节
    @ApiOperation(value = "根据ID修改章节")
    @ApiParam(name = "eduChapter", value = "章节对象", required = true)
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除章节
    @ApiOperation(value = "根据ID删除章节")
    @ApiParam(name = "chapterId", value = "章节ID", required = true)
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

