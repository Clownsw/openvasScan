package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
@Component
public final class OpenvasCommandStructParseTask implements OpenvasCommandStructParse<OpenvasTask> {
    private static final OpenvasCommandStructParseTask INSTANCE = new OpenvasCommandStructParseTask();

    public static OpenvasCommandStructParseTask getInstance() {
        return INSTANCE;
    }

    private OpenvasCommandStructParseTask() {
    }

    @Override
    public OpenvasTask parse(Element element) {
        return new OpenvasTask(
                element.getAttribute("id"),
                CommonUtil.elementGetFirstChild(element, "name"),
                CommonUtil.elementGetFirstChild(element, "comment"),
                CommonUtil.elementGetFirstChild(element, "status"),
                XmlUtil.getElement(element, "config").getAttribute("id"),
                XmlUtil.getElement(element, "target").getAttribute("id"),
                XmlUtil.getElement(element, "scanner").getAttribute("id"),
                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "creation_time").getFirstChild().getTextContent()),
                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "modification_time").getFirstChild().getTextContent())
        );
    }
}
