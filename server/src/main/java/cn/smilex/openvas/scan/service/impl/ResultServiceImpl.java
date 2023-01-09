package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.ResultService;
import org.springframework.stereotype.Service;

/**
 * @author smilex
 */
@Service
public class ResultServiceImpl implements ResultService {

    /**
     * 根据任务ID获取任务结果
     *
     * @param taskId 任务ID
     * @return 结果
     */
    @Override
    public String selectResultById(String taskId) {
        OpenvasCommandParse<Object> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_RESULT);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml(taskId));
    }
}
