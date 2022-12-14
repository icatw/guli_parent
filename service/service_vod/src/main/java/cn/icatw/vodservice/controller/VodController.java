package cn.icatw.vodservice.controller;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.commonutils.R;
import cn.icatw.vodservice.constant.AliConstant;
import cn.icatw.vodservice.service.VodService;
import cn.icatw.vodservice.utils.InitVodClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")

@Api(tags = "视频模块")
public class VodController {
    @Autowired
    private VodService vodService;
    @Autowired
    AliConstant aliConstant;

    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }

    @ApiOperation(value = "根据视频id删除阿里云视频")
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(aliConstant.getKeyId(), aliConstant.getKeySecret());
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置删除的视频id
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id list
    @ApiOperation(value = "删除多个阿里云视频")
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

    @GetMapping("get-play-auth/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(aliConstant.getKeyId(), aliConstant.getKeySecret());
            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            //返回结果
            return R.ok().message("获取凭证成功").data("playAuth", playAuth);
        } catch (ClientException e) {
            throw new GuliException(20001, "获取视频凭证失败！");
        }
    }
}
