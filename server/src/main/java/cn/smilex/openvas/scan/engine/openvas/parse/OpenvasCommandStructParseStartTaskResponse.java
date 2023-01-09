package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasStartTaskResponse;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructParseStartTaskResponse implements OpenvasCommandStructParse<OpenvasStartTaskResponse> {
    private static final OpenvasCommandStructParseStartTaskResponse INSTANCE = new OpenvasCommandStructParseStartTaskResponse();

    public static OpenvasCommandStructParseStartTaskResponse getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasStartTaskResponse parse(Element element) {
        return new OpenvasStartTaskResponse(
                element.getAttribute("id"),
                element.getAttribute("status"),
                element.getAttribute("status_text"),
                CommonUtil.elementGetFirstChild(element, "report_id")
        );
    }
}
