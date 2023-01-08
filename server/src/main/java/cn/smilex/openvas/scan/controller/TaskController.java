package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.TaskService;
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
@RestController
@CrossOrigin
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
}
