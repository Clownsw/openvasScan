package cn.smilex.openvas.scan.service;

import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.entity.CreateTask;

import java.util.Queue;

/**
 * <p>
 * 任务服务类
 * </p>
 *
 * @author smilex
 * @since 2022/09/30 06:21:16
 */
@SuppressWarnings("all")
public interface TaskService {

    /**
     * 获取所有task
     *
     * @return task list
     */
    Queue<OpenvasTask> selectAllTask();

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务
     */
    String selectTaskById(String taskId);

    /**
     * 创建任务
     *
     * @param createTask 创建任务对象
     * @return 结果
     */
    String createTask(CreateTask createTask);

    /**
     * 根据任务ID启动任务
     *
     * @param taskId 任务ID
     * @return 结果
     */
    String startTask(String taskId);
}
