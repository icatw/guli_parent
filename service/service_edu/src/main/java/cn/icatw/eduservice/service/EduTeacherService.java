package cn.icatw.eduservice.service;

import cn.icatw.eduservice.entity.EduTeacher;
import cn.icatw.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author icatw
 * @since 2022-09-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页条件查询
     *
     * @param pageParam    页面参数
     * @param teacherQuery 老师查询
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
