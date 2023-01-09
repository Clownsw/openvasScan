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
    private String comment;
    private String configId;
    private String targetId;
    private String scannerId;

    @Override
    public Map<String, Object> toMap() {
        return new HashMapBuilder<String, Object>(5)
                .put("taskName", this.taskName)
                .put("comment", this.comment)
                .put("configId", this.configId)
                .put("targetId", this.targetId)
                .put("scannerId", this.scannerId)
                .get();
    }
}
