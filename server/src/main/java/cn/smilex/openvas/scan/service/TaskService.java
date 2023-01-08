package cn.smilex.openvas.scan.service;

import cn.smilex.openvas.scan.pojo.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务服务类
 * </p>
 *
 * @author smilex
 * @since 2022/09/30 06:21:16
 */
@SuppressWarnings("all")
public interface TaskService extends IService<Task> {

    /**
     * 获取所有task
     *
     * @return task list
     */
    String selectAllTask();

    /**
     * 根据任务ID获取任务
     *
     * @param taskId 任务ID
     * @return 任务
     */
    String selectConfigById(String taskId);
}
