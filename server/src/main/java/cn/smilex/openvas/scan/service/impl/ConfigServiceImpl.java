package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.exception.OpenvasScanException;
import cn.smilex.openvas.scan.runtime.OpenvasProcessTask;
import cn.smilex.openvas.scan.runtime.ProcessTask;
import cn.smilex.openvas.scan.service.ConfigService;
import cn.smilex.openvas.scan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author smilex
 */
@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {

    private OpenvasEngine openvasEngine;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    /**
     * 获取所有config
     *
     * @return config list
     */
    @Override
    public String selectAllConfig() {
        OpenvasCommandParse<List<OpenvasConfig>> openvasCommandParse = openvasEngine.getCommandParseByCommand(OpenvasCommand.GET_CONFIGS);

        Future<ProcessTask> future = CommonUtil.submitTaskToCommonThreadPool(new OpenvasProcessTask(openvasCommandParse.getEmptyXml()));
        try {
            ProcessTask processTask = future.get();
            if (processTask.isError()) {
                return processTask.getErrorMsg();
            }
            return processTask.getResult();
        } catch (Exception e) {
            throw new OpenvasScanException(e.getMessage());
        }
    }
}
