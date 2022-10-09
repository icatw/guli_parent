package cn.icatw.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小结vo
 *
 * @author icatw
 * @date 2022/09/28
 */
@ApiModel(value = "小结信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoVo {
    private String id;
    private String title;
}

