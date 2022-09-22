package cn.icatw.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    //文件上传至阿里云
    String uploadFileAvatar(MultipartFile file);
}

