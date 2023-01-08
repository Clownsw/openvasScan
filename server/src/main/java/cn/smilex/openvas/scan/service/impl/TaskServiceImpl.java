package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.mapper.TaskDao;
import cn.smilex.openvas.scan.pojo.Task;
import cn.smilex.openvas.scan.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private OpenvasEngine openvasEngine;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    /**
     * 获取所有task
     *
     * @return task list
     */
    @Override
    public String selectAllTask() {
        OpenvasCommandParse<List<OpenvasConfig>> openvasCommandParse = openvasEngine.getCommandParseByCommand(OpenvasCommand.GET_TASKS);
        return openvasEngine.execute(openvasCommandParse.getEmptyXml());
    }
}
