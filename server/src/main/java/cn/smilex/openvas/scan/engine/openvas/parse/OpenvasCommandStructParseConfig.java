package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
@Component
public final class OpenvasCommandStructParseConfig implements OpenvasCommandStructParse<OpenvasConfig> {
    private static final OpenvasCommandStructParseConfig INSTANCE = new OpenvasCommandStructParseConfig();

    public static OpenvasCommandStructParseConfig getInstance() {
        return INSTANCE;
    }

    private OpenvasCommandStructParseConfig() {
    }

    @Override
    public OpenvasConfig parse(Element element) {
        return new OpenvasConfig(
                element.getAttribute("id"),
                CommonUtil.elementGetFirstChild(element, "name"),
                CommonUtil.elementGetFirstChild(element, "comment"),
                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "creation_time").getFirstChild().getTextContent()),
                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "modification_time").getFirstChild().getTextContent())
        );
    }
}
