package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.config.ResultCode;
import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.entity.CreateTask;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.TaskService;
import cn.smilex.openvas.scan.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 任务控制器
 * </p>
 *
 * @author smilex
 * @since 2022/09/30 06:21:16
 */
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    private OpenvasEngine openvasEngine;
    private TaskService taskService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 获取所有Task
     *
     * @return Task List
     */
    @GetMapping("/list")
    public Result<?> selectAllTask() {
        return Result.actionSuccess(
                openvasEngine.parse(
                        taskService.selectAllTask(),
                        OpenvasCommand.GET_TASKS
                )
        );
    }

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务
     */
    @GetMapping("/one")
    public Result<?> selectTaskById(@RequestParam("taskId") String taskId) {
        List<OpenvasTask> openvasTaskList = openvasEngine.parse(
                taskService.selectConfigById(taskId),
                OpenvasCommand.GET_TASK
        );

        return Result.actionSuccess(
                openvasTaskList.size() == 0 ? null : openvasTaskList.get(0)
        );
    }

    /**
     * 创建任务
     *
     * @param createTask 创建任务对象
     * @return 结果
     */
    @PostMapping("/createTask")
    public Result<?> createTask(@RequestBody CreateTask createTask) throws IllegalAccessException {
        if (ClassUtil.objIsNull(CreateTask.class, createTask)) {
            return Result.fromResultCode(ResultCode.ERROR_NOT_FOUND_REQUEST_PARAMS);
        }
        return Result.actionSuccess(
                openvasEngine.parse(
                        taskService.createTask(createTask),
                        OpenvasCommand.CREATE_TASK
                )
        );
    }

    /**
     * 根据任务ID启动任务
     *
     * @param taskId 任务ID
     * @return 结果
     */
    @GetMapping("/startTask")
    public Result<?> startTask(@RequestParam("taskId") String taskId) {
        return Result.actionSuccess(
                openvasEngine.parse(
                        taskService.startTask(taskId),
                        OpenvasCommand.START_TASK
                )
        );
    }
}
