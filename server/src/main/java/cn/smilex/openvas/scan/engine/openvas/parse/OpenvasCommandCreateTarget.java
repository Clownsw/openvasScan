package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import java.util.Map;

/**
 * @author smilex
 */
@Slf4j
public class OpenvasCommandCreateTarget implements OpenvasCommandParse<OpenvasCommonResponse> {
    private static final String ROOT_TAG_NAME = "create_target";

    @Override
    public OpenvasCommonResponse parse(String xml) {
        try {
            Document root = XmlUtil.parseXml(xml);
            return OpenvasCommandStructParseCommonResponse.getInstance().parse(XmlUtil.getRootElement(root));
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public String getXml(OpenvasParams params) {
        Map<String, Object> map = params.toMap();

        String value = new XmlTagBuilder(
                "name",
                (String) map.get("name")
        )
                .getXml() +
                new XmlTagBuilder(
                        "hosts",
                        (String) map.get("hosts")
                )
                        .getXml() +
                new XmlTagBuilder(
                        "port_range",
                        (String) map.get("port_range")
                )
                        .getXml();

        return new XmlTagBuilder(
                ROOT_TAG_NAME,
                value
        )
                .getXml();
    }
}
