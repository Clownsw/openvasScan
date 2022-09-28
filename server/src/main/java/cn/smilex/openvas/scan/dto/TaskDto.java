package cn.smilex.openvas.scan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @date 2022/10/2/22:06
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Integer taskType;
    private String guoKaiId;
}
