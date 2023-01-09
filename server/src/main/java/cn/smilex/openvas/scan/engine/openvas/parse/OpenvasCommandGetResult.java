package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasResult;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author smilex
 */
public class OpenvasCommandGetResult implements OpenvasCommandParse<List<OpenvasResult>> {
    private static final String ROOT_TAG_NAME = "get_results";

    @Override
    public List<OpenvasResult> parse(String xml) {
        return null;
    }

    @Override
    public String getEmptyXml(Object... params) {
        if (params.length == 1 && params[0] instanceof String && StringUtils.isNoneBlank((String) params[0])) {
            return new XmlTagBuilder(
                    ROOT_TAG_NAME,
                    new HashMapBuilder<String, Object>(2)
                            .put("task_id", params[0])
                            .put("details", "1")
                            .get()
            )
                    .getXml();
        }

        throw new IllegalArgumentException();
    }
}
