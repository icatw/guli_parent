package cn.icatw.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 76218
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
