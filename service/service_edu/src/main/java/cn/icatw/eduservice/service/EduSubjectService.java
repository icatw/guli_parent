package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduSubject;
import cn.icatw.eduservice.entity.vo.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author icatw
 * @date 2022/9/25
 * @email 762188827@qq.com
 * @apiNote
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 导入主题数据
     *
     * @param file              文件
     * @param eduSubjectService edu主题服务
     */
    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);

    /**
     * 获取所有树形课程分类数据
     *
     * @return {@link List}<{@link OneSubject}>
     */
    List<OneSubject> getAllOneTwoSubject();
}
