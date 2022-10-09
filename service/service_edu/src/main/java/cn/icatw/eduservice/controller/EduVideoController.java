package cn.icatw.eduservice.controller;


import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.EduVideo;
import cn.icatw.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    //添加小节
    @ApiOperation(value = "新增课时")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean save = videoService.save(eduVideo);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据ID查询课时
    @ApiParam(name = "id", value = "课时ID", required = true)
    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

    //修改小节
    @ApiOperation(value = "更新课时")
    @PostMapping("updateVideo")
    public R updateCourseInfo(@RequestBody EduVideo eduVideo) {
        boolean update = videoService.updateById(eduVideo);
        if (update) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //删除小节
    @PostMapping("{id}")
    @ApiOperation(value = "根据ID删除课时")
    @ApiParam(name = "id", value = "课时ID", required = true)
    public R deleteVideo(@PathVariable String id) {
        //TODO 删除小节的时候把视频也删掉
        boolean b = videoService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

