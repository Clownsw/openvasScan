package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 获取所有config
     *
     * @return config list
     */
    @GetMapping("/list")
    public Result<?> selectAllConfig() {
        return Result.actionSuccess(
                openvasEngine.parse(
                        taskService.selectAllTask(),
                        OpenvasCommand.GET_TASKS
                )
        );
    }
}
