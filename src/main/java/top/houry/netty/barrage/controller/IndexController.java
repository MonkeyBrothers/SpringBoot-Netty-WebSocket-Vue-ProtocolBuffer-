package top.houry.netty.barrage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Desc
 * @Author houry
 * @Date 2021/3/2 10:22
 **/
@Controller
public class IndexController {

    /**
     * 跳转到首页
     *
     * @return 首页
     */
    @GetMapping("index")
    public String index() {
        return "index";
    }
}
