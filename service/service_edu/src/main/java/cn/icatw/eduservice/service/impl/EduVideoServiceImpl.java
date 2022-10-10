package cn.icatw.eduservice.service.impl;

import cn.icatw.eduservice.entity.EduVideo;
import cn.icatw.eduservice.feignclient.VodClient;
import cn.icatw.eduservice.mapper.EduVideoMapper;
import cn.icatw.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    //@Override
    //public void removeVideoByCourseId(String courseId) {
    //    QueryWrapper<EduVideo> QueryWrapper = new QueryWrapper<>();
    //    QueryWrapper.eq("course_id", courseId);
    //    baseMapper.delete(QueryWrapper);
    //}

    @Override
    public void removeVideoByCourseId(String courseId) {
        //1.先删除所有阿里云视频信息
        //2.再删除数据库的小节视频信息
        //根据课程id查询所有视频id
        QueryWrapper<EduVideo> Wrapper = new QueryWrapper<>();
        Wrapper.eq("course_id", courseId);
        Wrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(Wrapper);
        //List<EduVideo>变成List<string>
        //获取video中的视频源id--VideoSourceId
        List<String> videoIds = new ArrayList<>();
        videoList.forEach(video -> {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        });
        //for (int i = 0; i < videoList.size(); i++) {
        //    EduVideo eduVideo = videoList.get(i);
        //    if (!StringUtils.isEmpty(eduVideo)) {
        //        String videoSourceId = eduVideo.getVideoSourceId();
        //        videoIds.add(videoSourceId);
        //    }
        //}
        if (videoIds.size() > 0) {
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("course_id", courseId);
        baseMapper.delete(QueryWrapper);
    }

}
