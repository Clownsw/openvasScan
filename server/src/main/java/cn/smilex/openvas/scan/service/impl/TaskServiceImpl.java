package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.entity.CreateTask;
import cn.smilex.openvas.scan.mapper.TaskDao;
import cn.smilex.openvas.scan.pojo.Task;
import cn.smilex.openvas.scan.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {
    /**
     * 获取所有Task
     *
     * @return Task List
     */
    @Override
    public String selectAllTask() {
        OpenvasCommandParse<List<OpenvasTask>> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_TASKS);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml());
    }

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务
     */
    @Override
    public String selectConfigById(String taskId) {
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
}
