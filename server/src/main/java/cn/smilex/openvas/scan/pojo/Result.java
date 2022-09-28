package cn.smilex.openvas.scan.pojo;

import cn.smilex.openvas.scan.config.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用结果
 *
 * @author smilex
 * @date 2022/9/29/0:50
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> fromResultCode(ResultCode resultCode, String format, T data) {
        return new Result<>(
                resultCode.getCode(),
                String.format(resultCode.getMessage(), format),
                data
        );
    }

    public static Result<?> fromResultCode(ResultCode resultCode) {
        return fromResultCode(resultCode, null, null);
    }

    public static Result<?> fromResultCode(ResultCode resultCode, String format) {
        return fromResultCode(resultCode, format, null);
    }

    public static <T> Result<T> actionSuccess(T data) {
        return fromResultCode(ResultCode.ACTION_SUCCESS, null, data);
    }

    public static Result<?> actionSuccess() {
        return actionSuccess(null);
    }

    public static Result<?> actionError() {
        return fromResultCode(ResultCode.ACTION_ERROR);
    }
}
