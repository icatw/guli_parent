package cn.icatw.eduservice.controller;

import cn.icatw.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author icatw
 * @date 2022/9/21
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar",
                        "http://qzapp.qlogo.cn/qzapp/101983660/4697C6ECF9F91EBE2FF983A6D03F536D/100");
    }
}
