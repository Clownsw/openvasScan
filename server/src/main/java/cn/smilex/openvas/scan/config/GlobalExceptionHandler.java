package cn.smilex.openvas.scan.config;

import cn.smilex.openvas.scan.exception.OpenvasScanException;
import cn.smilex.openvas.scan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author smilex
 * @date 2022/9/29/0:49
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<?> error(Exception e) {
        log.error("", e);
        return Result.fromResultCode(ResultCode.UNKNOWN_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(OpenvasScanException.class)
    public Result<?> error(OpenvasScanException e) {
        log.error("", e);
        return Result.fromResultCode(e.getResultCode());
    }

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> error(HttpRequestMethodNotSupportedException e) {
        log.error("", e);
        return Result.fromResultCode(ResultCode.SYSTEM_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public Result<?> error(NullPointerException e) {
        log.error("", e);
        return Result.fromResultCode(ResultCode.SYSTEM_ERROR);
    }
}
