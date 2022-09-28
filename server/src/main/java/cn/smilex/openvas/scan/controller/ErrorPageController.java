package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.pojo.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smilex
 * @date 2022/9/30/20:46
 * @since 1.0
 */
@CrossOrigin
@RestController
public class ErrorPageController implements ErrorController {

    @RequestMapping("/error")
    public Result<?> errorJsonPage() {
        return Result.actionError();
    }
}
