package cn.icatw.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icatw
 * @date 2022/9/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
