package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasScanner;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructParseScanner implements OpenvasCommandStructParse<OpenvasScanner> {
    private static final OpenvasCommandStructParseScanner INSTANCE = new OpenvasCommandStructParseScanner();

    public static OpenvasCommandStructParseScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasScanner parse(Element element) {
        return new OpenvasScanner(
                element.getAttribute("id"),
                CommonUtil.elementGetFirstChild(element, "name"),
                CommonUtil.parseUtcTime(CommonUtil.elementGetFirstChild(element, "creation_time")),
                CommonUtil.parseUtcTime(CommonUtil.elementGetFirstChild(element, "modification_time"))
        );
    }
}
