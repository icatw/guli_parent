package cn.icatw.eduservice.controller;


import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.commonutils.R;
import cn.icatw.eduservice.entity.EduVideo;
import cn.icatw.eduservice.feignclient.VodClient;
import cn.icatw.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
@Api(tags = "课程视频管理")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

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

    @PostMapping("{id}")
    @ApiOperation(value = "根据ID删除课时")
    @ApiParam(name = "id", value = "课时ID", required = true)
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.removeAlyVideo(videoSourceId);
            if (r.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，触发熔断...");
            }
        }
        boolean b = videoService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

