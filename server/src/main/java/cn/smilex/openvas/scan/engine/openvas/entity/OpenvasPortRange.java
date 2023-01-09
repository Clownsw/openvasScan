package cn.smilex.openvas.scan.engine.openvas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenvasPortRange {
    private String id;
    private String start;
    private String end;
    private String type;
    private String comment;
}
