package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;

import java.util.Map;

/**
 * @author smilex
 */
public class OpenvasCommandCreateTask implements OpenvasCommandParse<Object> {
    private static final String ROOT_TAG_NAME = "create_task";

    @Override
    public Object parse(String xml) {
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
                        (String) map.get("comment")
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
