package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructParseCommonResponse implements OpenvasCommandStructParse<OpenvasCommonResponse> {
    private static final OpenvasCommandStructParseCommonResponse INSTANCE = new OpenvasCommandStructParseCommonResponse();

    public static OpenvasCommandStructParseCommonResponse getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasCommonResponse parse(Element element) {
        return new OpenvasCommonResponse(
                element.getAttribute("id"),
                element.getAttribute("status"),
                element.getAttribute("status_text")
        );
    }
}
