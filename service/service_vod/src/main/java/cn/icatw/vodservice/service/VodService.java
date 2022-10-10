package cn.icatw.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 76218
 */
public interface VodService {
    /**
     * 上传视频阿里
     *
     * @param file 文件
     * @return {@link String}
     */
    String uploadVideoAly(MultipartFile file);

    /**
     * 删除更多视频
     *
     * @param videoIdList 视频id列表
     */
    void removeMoreAlyVideo(List videoIdList);

}
