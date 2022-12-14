package cn.icatw.eduservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.eduservice.entity.EduChapter;
import cn.icatw.eduservice.entity.EduVideo;
import cn.icatw.eduservice.entity.chapter.ChapterVo;
import cn.icatw.eduservice.entity.chapter.VideoVo;
import cn.icatw.eduservice.mapper.EduChapterMapper;
import cn.icatw.eduservice.service.EduChapterService;
import cn.icatw.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id找到章节信息
        //2.根据课程id和章节id找到小结信息list
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();

        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        //课程对应的章节list
        List<EduChapter> chapterList = this.list(queryWrapper);
        chapterList.forEach(chapter -> {
            //复制bean属性
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            String chapterId = chapter.getId();
            QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("chapter_id", chapterId).eq("course_id", courseId);
            List<EduVideo> videoList = eduVideoService.list(videoQueryWrapper);
            List<VideoVo> videoVoList = videoList.stream().map(video -> {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                return videoVo;
            }).collect(Collectors.toList());
            chapterVo.setChildren(videoVoList);
            chapterVos.add(chapterVo);
        });
        return chapterVos;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(QueryWrapper);
        //判断
        if (count > 0) {//能查询出来小节,不进行删除
            throw new GuliException(20001, "不能删除");
        } else { //没有查询到数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }


}
