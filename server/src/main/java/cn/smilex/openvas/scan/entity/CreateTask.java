package cn.smilex.openvas.scan.entity;

import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import lombok.Data;

import java.util.Map;

/**
 * @author smilex
 */
@Data
public class CreateTask implements OpenvasParams {
    private String taskName;
    private String configId;
    private String targetId;
    private String scannerId;
    private String tcpPort;
    private String updPort;
    private String hosts;

    @Override
    public Map<String, Object> toMap() {
        return new HashMapBuilder<String, Object>(4)
                .put("taskName", this.taskName)
                .put("configId", this.configId)
                .put("targetId", this.targetId)
                .put("scannerId", this.scannerId)
                .get();
    }
}
