package cn.icatw.eduservice.listener;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.eduservice.entity.EduSubject;
import cn.icatw.eduservice.entity.excel.ExcelSubjectData;
import cn.icatw.eduservice.service.EduSubjectService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * 主题excel侦听器
 *
 * @author 76218
 * @date 2022/09/25
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(ExcelSubjectData user, AnalysisContext analysisContext) {
        if (user == null) {
            throw new GuliException(20001, "添加失败");
        }
        //添加一级分类
        EduSubject existOneSubject =
                this.existOneSubject(subjectService, user.getOneSubjectName());
        if (existOneSubject == null) {//没有相同的
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(user.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值
        String pid = existOneSubject.getId();
        //添加二级分类
        EduSubject existTwoSubject =
                this.existTwoSubject(subjectService, user.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(user.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext
            context) {
        System.out.println("表头信息：" + headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String
            name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService, String
            name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }
}
