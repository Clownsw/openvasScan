package cn.smilex.openvas.scan.engine.openvas;

import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandGetConfigs;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author smilex
 */
@SuppressWarnings("unchecked")
@Component
public class OpenvasEngine {
    private static final Map<OpenvasCommand, OpenvasCommandParse<?>> OPENVAS_COMMAND_PARSE_MAP;

    static {
        OPENVAS_COMMAND_PARSE_MAP = new HashMapBuilder<OpenvasCommand, OpenvasCommandParse<?>>(1)
                .put(OpenvasCommand.GET_CONFIGS, new OpenvasCommandGetConfigs())
                .get();
    }

    /**
     * 解析xml
     *
     * @param xml            xml
     * @param openvasCommand command
     * @param <T>            result type
     * @return result
     */
    public <T> T parse(String xml, OpenvasCommand openvasCommand) {
        return (T) OPENVAS_COMMAND_PARSE_MAP.get(openvasCommand).parse(xml);
    }

    /**
     * 根据openvas命令获取对应的解析器
     *
     * @param openvasCommand openvas命令
     * @param <T>            unknown type
     * @return 解析器
     */
    public <T> OpenvasCommandParse<T> getCommandParseByCommand(OpenvasCommand openvasCommand) {
        return (OpenvasCommandParse<T>) OPENVAS_COMMAND_PARSE_MAP.get(openvasCommand);
    }
}
