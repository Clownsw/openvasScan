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
public class OpenvasStartTaskResponse {
    private String id;
    private String status;
    private String statusText;
    private String reportId;

    public boolean isOk() {
        return statusText != null && statusText.startsWith("OK");
    }
}
