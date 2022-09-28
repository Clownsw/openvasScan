package cn.smilex.openvas.scan.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @date 2022/10/6/5:29
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig {
    private Boolean enableLogin;
    private Boolean enableRegister;
    private Boolean autoCommit;
    private Boolean autoView;
}
