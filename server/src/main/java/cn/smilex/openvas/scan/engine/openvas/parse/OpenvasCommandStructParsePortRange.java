package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasPortRange;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructParsePortRange implements OpenvasCommandStructParse<OpenvasPortRange> {
    private static final OpenvasCommandStructParsePortRange INSTANCE = new OpenvasCommandStructParsePortRange();

    public static OpenvasCommandStructParsePortRange getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasPortRange parse(Element element) {
        return new OpenvasPortRange(
                element.getAttribute("id"),
                CommonUtil.elementGetFirstChild(element, "start"),
                CommonUtil.elementGetFirstChild(element, "end"),
                CommonUtil.elementGetFirstChild(element, "type"),
                CommonUtil.elementGetFirstChild(element, "comment")
        );
    }
}
