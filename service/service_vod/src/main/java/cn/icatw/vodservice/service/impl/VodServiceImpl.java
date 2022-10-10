package cn.icatw.vodservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.vodservice.constant.AliConstant;
import cn.icatw.vodservice.service.VodService;
import cn.icatw.vodservice.utils.InitVodClient;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author icatw
 * @date 2022/10/10
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {
    @Autowired
    private AliConstant aliConstant;

    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            //accessKeyId, accessKeySecret
            //fileName：上传文件原始名称
            // 01.03.09.mp4
            String fileName = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(aliConstant.getKeyId(), aliConstant.getKeySecret(), title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
                log.error("-------------上传失败------------");
                log.error("视频id：" + response.getVideoId());
                log.error("code：" + response.getCode());
                log.error("msg：" + response.getMessage());
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(999, "视频上传失败！");
        }
    }

    @Override
    public void removeMoreAlyVideo(List videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(aliConstant.getKeyId(), aliConstant.getKeySecret());
            //创建删除视频的request对象
            DeleteVideoRequest request=new DeleteVideoRequest();
            //向request设置删除的视频id
            String ids = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(ids);
            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }

}
