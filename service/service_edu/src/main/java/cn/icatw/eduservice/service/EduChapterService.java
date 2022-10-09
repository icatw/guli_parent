package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduChapter;
import cn.icatw.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-09-27
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程id获取章节信息
     *
     * @param courseId 进程id
     * @return {@link List}<{@link ChapterVo}>
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
