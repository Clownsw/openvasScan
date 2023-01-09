package cn.smilex.openvas.scan.engine.openvas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smilex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenvasPortList {
    private String id;
    private List<OpenvasPortRange> openvasPortRangeList = new ArrayList<>();
}
