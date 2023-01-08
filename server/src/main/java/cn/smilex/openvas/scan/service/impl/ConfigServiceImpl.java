package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.exception.OpenvasScanException;
import cn.smilex.openvas.scan.runtime.OpenvasProcessTask;
import cn.smilex.openvas.scan.runtime.ProcessTask;
import cn.smilex.openvas.scan.service.ConfigService;
import cn.smilex.openvas.scan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author smilex
 */
@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {
    /**
     * 获取所有config
     *
     * @return config list
     */
    @Override
    public String selectAllConfig() {
        Future<ProcessTask> future = CommonUtil.submitTaskToCommonThreadPool(new OpenvasProcessTask("<get_configs></get_configs>"));
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
