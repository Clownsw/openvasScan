package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.entity.CreateTask;
import cn.smilex.openvas.scan.entity.StructuredTaskScope;
import cn.smilex.openvas.scan.pojo.Tuple;
import cn.smilex.openvas.scan.service.TaskService;
import cn.smilex.openvas.scan.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 * 任务服务实现类
 * </p>
 *
 * @author smilex
 * @since 2022/09/30 06:21:16
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    public static final Path OPENVAS_TASK_BACK_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "task");
    private static BlockingQueue<Tuple<String, String>> OPENVAS_TASK_ID_QUEUE = new LinkedBlockingQueue<>();

    private OpenvasEngine openvasEngine;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    public static synchronized void readOrWriteOpenvasTaskIdCache(boolean isRead) {
        try {
            if (isRead) {
                File file = OPENVAS_TASK_BACK_PATH.toFile();
                if (!file.exists() || !file.isFile()) {
                    return;
                }

                OPENVAS_TASK_ID_QUEUE = JsonUtil.OBJECT_MAPPER.readValue(
                        Files.readAllBytes(OPENVAS_TASK_BACK_PATH),
                        new TypeReference<LinkedBlockingQueue<Tuple<String, String>>>() {
                        }
                );
            } else {
                Files.write(
                        OPENVAS_TASK_BACK_PATH,
                        JsonUtil.toJson(OPENVAS_TASK_ID_QUEUE).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE
                );
            }
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static void addToOpenvasTaskIdQueue(String taskId, String reportId) {
        OPENVAS_TASK_ID_QUEUE.add(new Tuple<>(taskId, reportId));

        readOrWriteOpenvasTaskIdCache(false);
    }

    public static Tuple<String, String> getOpenvasTaskIdById(String taskId) {
        for (Tuple<String, String> openvasTaskId : OPENVAS_TASK_ID_QUEUE) {
            if (taskId.equals(openvasTaskId.getLeft())) {
                return openvasTaskId;
            }
        }

        return null;
    }

    /**
     * 获取所有Task
     *
     * @return Task List
     */
    @Override
    public Queue<OpenvasTask> selectAllTask() {
        BlockingQueue<OpenvasTask> openvasTaskBlockingQueue = new LinkedBlockingQueue<>();

        try (StructuredTaskScope scope = new StructuredTaskScope(true, OPENVAS_TASK_ID_QUEUE.size())) {
            for (Tuple<String, String> openvasTaskId : OPENVAS_TASK_ID_QUEUE) {
                scope.fork(() -> {
                    List<OpenvasTask> openvasTaskList = openvasEngine.parse(
                            selectTaskById(openvasTaskId.getLeft()),
                            OpenvasCommand.GET_TASK
                    );

                    if (openvasTaskList.size() == 1) {
                        openvasTaskBlockingQueue.add(openvasTaskList.get(0));
                    }
                });
            }

            return openvasTaskBlockingQueue;
        } catch (Exception ignore) {
        }

        return null;
    }

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务
     */
    @Override
    public String selectTaskById(String taskId) {
        OpenvasCommandParse<List<OpenvasTask>> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_TASK);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml(taskId));
    }


    /**
     * 创建任务
     *
     * @param createTask 创建任务对象
     * @return 结果
     */
    @Override
    public String createTask(CreateTask createTask) {
        OpenvasCommandParse<List<OpenvasTask>> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.CREATE_TASK);
        return OpenvasEngine.execute(openvasCommandParse.getXml(createTask));
    }

    /**
     * 根据任务ID启动任务
     *
     * @param taskId 任务ID
     * @return 结果
     */
    @Override
    public String startTask(String taskId) {
        OpenvasCommandParse<OpenvasCommonResponse> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.START_TASK);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml(taskId));
    }
}
