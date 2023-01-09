package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import org.w3c.dom.Element;

/**
 * @author smilex
 */
public class OpenvasCommandStructCommonResponse implements OpenvasCommandStructParse<OpenvasCommonResponse> {
    private static final OpenvasCommandStructCommonResponse INSTANCE = new OpenvasCommandStructCommonResponse();

    public static OpenvasCommandStructCommonResponse getInstance() {
        return INSTANCE;
    }

    @Override
    public OpenvasCommonResponse parse(Element element) {
        return new OpenvasCommonResponse(
                element.getAttribute("status"),
                element.getAttribute("status_text")
        );
    }
}
