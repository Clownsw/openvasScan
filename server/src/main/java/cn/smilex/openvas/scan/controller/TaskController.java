package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.config.ResultCode;
import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasCommonResponse;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.entity.CreateTarget;
import cn.smilex.openvas.scan.entity.CreateTask;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.TargetService;
import cn.smilex.openvas.scan.service.TaskService;
import cn.smilex.openvas.scan.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    private TargetService targetService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
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
        if (ClassUtil.objIsNull(CreateTask.class, createTask, "targetId", "tcpPort", "updPort")) {
            return Result.fromResultCode(ResultCode.ERROR_NOT_FOUND_REQUEST_PARAMS);
        }

        if (StringUtils.isBlank(createTask.getTcpPort()) && StringUtils.isBlank(createTask.getUpdPort())) {
            createTask.setTcpPort("3306");
        }

        String portRange = "T:" + createTask.getTcpPort() + (StringUtils.isBlank(createTask.getUpdPort()) ? CommonConfig.EMPTY_STRING : ",U:" + createTask.getUpdPort());

        OpenvasCommonResponse openvasCommonResponse = openvasEngine.parse(
                targetService.createTarget(
                        new CreateTarget(
                                UUID.randomUUID().toString(),
                                createTask.getHosts(),
                                portRange
                        )
                ),
                OpenvasCommand.CREATE_TARGET
        );

        if (openvasCommonResponse == null) {
            return Result.fromResultCode(ResultCode.ERROR_CREATE_TASK_ERROR);
        }

        createTask.setTargetId(openvasCommonResponse.getId());


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
