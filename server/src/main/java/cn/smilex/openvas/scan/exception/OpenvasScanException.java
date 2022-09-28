package cn.smilex.openvas.scan.exception;

import cn.smilex.openvas.scan.config.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author smilex
 * @date 2022/9/29/18:45
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OpenvasScanException extends RuntimeException {
    private ResultCode resultCode;

    public OpenvasScanException(String message) {
        super(message);
    }

    public OpenvasScanException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }
}
