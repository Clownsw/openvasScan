package cn.smilex.openvas.scan.config;

import lombok.Getter;

/**
 * @author smilex
 * @date 2022/9/29/0:51
 * @since 1.0
 */
@Getter
public enum ResultCode {
    ACTION_SUCCESS(1000, "操作成功!"),
    ACTION_ERROR(2000, "操作失败!"),
    UNKNOWN_ERROR(2001, "未知的错误!"),
    SYSTEM_ERROR(2002, "系统错误!"),
    ERROR_NOT_FOUND_REQUEST_PARAMS(3000, "错误, 缺少请求参数!");

    final Integer code;
    final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
