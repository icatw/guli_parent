package cn.icatw.eduservice.service.impl;

import cn.icatw.baseservice.exception.GuliException;
import cn.icatw.eduservice.entity.EduSubject;
import cn.icatw.eduservice.entity.excel.ExcelSubjectData;
import cn.icatw.eduservice.entity.vo.OneSubject;
import cn.icatw.eduservice.entity.vo.TwoSubject;
import cn.icatw.eduservice.listener.SubjectExcelListener;
import cn.icatw.eduservice.mapper.EduSubjectMapper;
import cn.icatw.eduservice.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icatw
 * @date 2022/9/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取

            EasyExcel.read(in, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            throw new GuliException(20002, "添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<EduSubject> oneSubjectList = this.list(wrapper);
        //1.获取所有1级分类（pid为0），得到1级分类的id和title（用于设置进oneSub）
        //2.根据1级分类id查找2级分类设置进twoSub
        ArrayList<OneSubject> oneSubjects = new ArrayList<>();
        ArrayList<TwoSubject> twoSubjects = new ArrayList<>();
        oneSubjectList.forEach(oneSub -> {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(oneSub, oneSubject);
            String pId = oneSub.getId();
            QueryWrapper<EduSubject> twoSubQueryWrapper = new QueryWrapper<>();
            twoSubQueryWrapper.eq("parent_id", pId);
            List<EduSubject> twoSubList = this.list(twoSubQueryWrapper);
            twoSubList.forEach(twoSubject -> {
                TwoSubject twoSub = new TwoSubject();
                BeanUtils.copyProperties(twoSubject, twoSub);
                twoSubjects.add(twoSub);
            });
            oneSubject.setChildren(twoSubjects);
            oneSubjects.add(oneSubject);
        });
        return oneSubjects;
    }
}
