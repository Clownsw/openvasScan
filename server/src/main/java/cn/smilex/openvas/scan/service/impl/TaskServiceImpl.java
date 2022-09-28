package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.mapper.TaskDao;
import cn.smilex.openvas.scan.pojo.Task;
import cn.smilex.openvas.scan.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
