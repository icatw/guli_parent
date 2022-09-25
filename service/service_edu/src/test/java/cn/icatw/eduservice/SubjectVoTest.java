package cn.icatw.eduservice;

import cn.icatw.eduservice.entity.vo.OneSubject;
import cn.icatw.eduservice.service.EduSubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author icatw
 * @date 2022/9/25
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootTest
public class SubjectVoTest {
    @Autowired
    private EduSubjectService eduSubjectService;

    @Test
    public void testSubject() {
        for (OneSubject oneSubject : eduSubjectService.getAllOneTwoSubject()) {
            System.out.println(oneSubject);
        }

    }
}
