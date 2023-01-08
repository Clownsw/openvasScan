package cn.smilex.openvas.scan.pojo;

import cn.smilex.openvas.scan.config.CommonConfig;
import lombok.Data;

import java.util.Map;

/**
 * @author smilex
 */
@SuppressWarnings("unchecked")
@Data
public final class XmlTagBuilder {
    private final String tagName;
    private final Map<String, Object> attributeMap;
    private final String value;

    public XmlTagBuilder(String tagName) {
        this(tagName, (Map<String, Object>) CommonConfig.EMPTY_MAP, CommonConfig.EMPTY_STRING);
    }

    public XmlTagBuilder(String tagName, Map<String, Object> attributeMap) {
        this(tagName, attributeMap, CommonConfig.EMPTY_STRING);
    }

    public XmlTagBuilder(String tagName, String value) {
        this(tagName, (Map<String, Object>) CommonConfig.EMPTY_MAP, value);
    }

    public XmlTagBuilder(String tagName, Map<String, Object> attributeMap, String value) {
        this.tagName = tagName;
        this.attributeMap = attributeMap;
        this.value = value;
    }

    public String getXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<")
                .append(this.tagName);

        this.attributeMap.keySet().forEach(key -> {
            Object value = this.attributeMap.get(key);
            sb.append(" ")
                    .append(key)
                    .append("=")
                    .append("\\\"")
                    .append(value)
                    .append("\\\"");
        });

        sb.append(">")
                .append(this.value)
                .append("</")
                .append(this.tagName)
                .append(">");

        return sb.toString();
    }
}
