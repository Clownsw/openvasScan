package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import java.util.Map;

/**
 * @author smilex
 */
@Slf4j
public class OpenvasCommandCreateTask implements OpenvasCommandParse<OpenvasCommonResponse> {
    private static final String ROOT_TAG_NAME = "create_task";

    @Override
    public OpenvasCommonResponse parse(String xml) {
        try {
            Document root = XmlUtil.parseXml(xml);
            return OpenvasCommandStructCommonResponse.getInstance().parse(XmlUtil.getRootElement(root));
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public String getXml(OpenvasParams params) {
        Map<String, Object> map = params.toMap();

        String sb = new XmlTagBuilder(
                "name",
                (String) map.get("taskName")
        )
                .getXml() +
                new XmlTagBuilder(
                        "comment",
                        CommonConfig.EMPTY_STRING
                )
                        .getXml() +
                new XmlTagBuilder(
                        "config",
                        new HashMapBuilder<String, Object>(1)
                                .put("id", map.get("configId"))
                                .get()
                )
                        .getXml() +
                new XmlTagBuilder(
                        "target",
                        new HashMapBuilder<String, Object>(1)
                                .put("id", map.get("targetId"))
                                .get()
                )
                        .getXml() +
                new XmlTagBuilder(
                        "scanner",
                        new HashMapBuilder<String, Object>(1)
                                .put("id", map.get("scannerId"))
                                .get()
                )
                        .getXml();
        return new XmlTagBuilder(
                ROOT_TAG_NAME,
                sb
        )
                .getXml();
    }
}
