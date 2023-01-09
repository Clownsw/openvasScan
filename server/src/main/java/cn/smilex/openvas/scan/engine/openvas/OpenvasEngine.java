package cn.smilex.openvas.scan.engine.openvas;

import cn.smilex.openvas.scan.engine.openvas.parse.*;
import cn.smilex.openvas.scan.exception.OpenvasScanException;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.runtime.OpenvasProcessTask;
import cn.smilex.openvas.scan.runtime.ProcessTask;
import cn.smilex.openvas.scan.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author smilex
 */
@SuppressWarnings("unchecked")
@Component
public class OpenvasEngine {
    private static final Map<OpenvasCommand, OpenvasCommandParse<?>> OPENVAS_COMMAND_PARSE_MAP;

    static {
        OPENVAS_COMMAND_PARSE_MAP = new HashMapBuilder<OpenvasCommand, OpenvasCommandParse<?>>(5)
                .put(OpenvasCommand.GET_CONFIGS, new OpenvasCommandGetConfigs())
                .put(OpenvasCommand.GET_CONFIG, new OpenvasCommandGetConfig())
                .put(OpenvasCommand.GET_TASKS, new OpenvasCommandGetTasks())
                .put(OpenvasCommand.GET_TASK, new OpenvasCommandGetTask())
                .put(OpenvasCommand.CREATE_TASK, new OpenvasCommandCreateTask())
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
    public static <T> OpenvasCommandParse<T> getCommandParseByCommand(OpenvasCommand openvasCommand) {
        return (OpenvasCommandParse<T>) OPENVAS_COMMAND_PARSE_MAP.get(openvasCommand);
    }

    /**
     * 执行命令
     *
     * @param command 命令
     * @return result
     */
    public static String execute(String command) {
        Future<ProcessTask> future = CommonUtil.submitTaskToCommonThreadPool(new OpenvasProcessTask(command));

        try {
            ProcessTask processTask = future.get();
            if (processTask.isError()) {
                return processTask.getErrorMsg();
            }
            return processTask.getResult();
        } catch (Exception e) {
            throw new OpenvasScanException(e.getMessage());
        }
    }
}
