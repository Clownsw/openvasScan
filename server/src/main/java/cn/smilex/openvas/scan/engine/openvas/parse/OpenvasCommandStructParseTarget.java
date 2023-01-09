package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTarget;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructParseTarget implements OpenvasCommandStructParse<OpenvasTarget> {

    private static final OpenvasCommandStructParseTarget INSTANCE = new OpenvasCommandStructParseTarget();

    public static OpenvasCommandStructParseTarget getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasTarget parse(Element element) {
        return new OpenvasTarget(
                element.getAttribute("id"),
                CommonUtil.elementGetFirstChild(element, "name"),
                CommonUtil.elementGetFirstChild(element, "comment"),
                CommonUtil.parseUtcTime(CommonUtil.elementGetFirstChild(element, "creation_time")),
                CommonUtil.parseUtcTime(CommonUtil.elementGetFirstChild(element, "modification_time")),
                XmlUtil.getElement(element, "port_list").getAttribute("id"),
                null
        );
    }
}
