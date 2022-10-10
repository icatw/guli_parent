package cn.icatw.vodservice;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/10
 * @email 762188827@qq.com
 * @apiNote
 */
public class TestVod {
    public static void main(String[] args) throws ClientException, com.aliyuncs.exceptions.ClientException {
        //getVideoInfo();
        testUpload();
    }

    private static void getVideoInfo() throws com.aliyuncs.exceptions.ClientException {
        //根据视频id获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client =InitObject.initVodClient("LTAI5t9ijEX8ZekqyRJAn2xr", "sQe0ZMetpW65VnS9dSB9zgYUeYuArU");
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        GetPlayInfoRequest request=new GetPlayInfoRequest();
        //向request对象里面设置视频id
        request.setVideoId("d9d7810400014fd5806cc6335f14bbb9");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    public static void testUpload() {
            String accessKeyId = "LTAI5t9ijEX8ZekqyRJAn2xr";
            String accessKeySecret = "sQe0ZMetpW65VnS9dSB9zgYUeYuArU";

            String title = "6 - What If I Want to Move Faster - upload by sdk";   //上传之后文件名称
            String fileName = "D:/一些项目/谷粒学院/项目资料/1-阿里云上传测试视频/6 - What If I Want to Move Faster.mp4";  //本地文件路径和名称
            //上传视频的方法
            UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
            /* 可指定分片上传时每个分片的大小，默认为2M字节 */
            request.setPartSize(2 * 1024 * 1024L);
            /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
            request.setTaskNum(1);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadVideoResponse response = uploader.uploadVideo(request);

            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else {
                /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
        }
}
