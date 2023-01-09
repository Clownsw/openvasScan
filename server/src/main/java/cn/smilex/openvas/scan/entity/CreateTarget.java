package cn.smilex.openvas.scan.entity;

import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author smilex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTarget implements OpenvasParams {
    private String name;
    private String hosts;
    private String portRange;

    @Override
    public Map<String, Object> toMap() {
        return new HashMapBuilder<String, Object>(3)
                .put("name", this.name)
                .put("hosts", this.hosts)
                .put("port_range", this.portRange)
                .get();
    }
}
