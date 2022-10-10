package cn.icatw.vodservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.vodservice.constant.AliConstant;
import cn.icatw.vodservice.service.VodService;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
