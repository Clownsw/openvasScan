package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

/**
 * @author smilex
 */
@SuppressWarnings("DuplicatedCode")
@Slf4j
public class OpenvasCommandStartTask implements OpenvasCommandParse<OpenvasCommonResponse> {
    private static final String ROOT_TAG_NAME = "start_task";

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
    public String getEmptyXml(Object... params) {
        if (params.length == 1 && params[0] instanceof String && StringUtils.isNoneBlank((String) params[0])) {
            return new XmlTagBuilder(
                    ROOT_TAG_NAME,
                    new HashMapBuilder<String, Object>(1)
                            .put("task_id", params[0])
                            .get()
            )
                    .getXml();
        }

        throw new IllegalArgumentException();
    }
}
