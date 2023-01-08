package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author smilex
 */
@SuppressWarnings({"DuplicatedCode"})
@Slf4j
public class OpenvasCommandGetConfig implements OpenvasCommandParse<List<OpenvasConfig>> {
    private static final String ROOT_TAG_NAME = "get_configs";

    /**
     * 解析xml
     *
     * @param xml xml
     * @return result
     */
    @Override
    public List<OpenvasConfig> parse(String xml) {
        return OpenvasEngine.<List<OpenvasConfig>>getCommandParseByCommand(OpenvasCommand.GET_CONFIG).parse(xml);
    }

    /**
     * 获取空标签
     *
     * @param params 参数
     * @return xml
     */
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
