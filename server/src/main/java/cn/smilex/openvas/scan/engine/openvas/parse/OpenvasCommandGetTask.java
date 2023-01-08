package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
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
public class OpenvasCommandGetTask implements OpenvasCommandParse<List<OpenvasTask>> {
    private static final String ROOT_TAG_NAME = "get_tasks";

    @Override
    public List<OpenvasTask> parse(String xml) {
        return OpenvasEngine.<List<OpenvasTask>>getCommandParseByCommand(OpenvasCommand.GET_TASKS).parse(xml);
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
