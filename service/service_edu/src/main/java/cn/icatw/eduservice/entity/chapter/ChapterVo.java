package cn.icatw.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节vo
 *
 * @author icatw
 * @date 2022/09/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "章节信息")
public class ChapterVo {
    private String id;
    private String title;
    //小结
    private List<VideoVo> children = new ArrayList<>();
}
