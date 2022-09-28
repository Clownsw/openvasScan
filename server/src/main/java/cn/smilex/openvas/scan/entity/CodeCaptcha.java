package cn.smilex.openvas.scan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @date 2022/10/1/18:43
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeCaptcha {
    private String id;
    private String ip;
    private String code;
    private String image;
}
