package cn.icatw.ossservice.controller;

import cn.icatw.commonutils.R;
import cn.icatw.ossservice.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(tags = "阿里云文件管理")
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        //获取上传文件 MultipartFile
        //返回上传路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().message("文件上传成功").data("url", url);
    }
}
