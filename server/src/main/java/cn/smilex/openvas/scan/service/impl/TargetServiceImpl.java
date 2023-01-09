package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.entity.CreateTarget;
import cn.smilex.openvas.scan.service.TargetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smilex
 */
@Service
public class TargetServiceImpl implements TargetService {

    /**
     * 创建扫描目标
     *
     * @param createTarget 扫描目标对象
     * @return 结果
     */
    @Override
    public String createTarget(CreateTarget createTarget) {
        OpenvasCommandParse<List<OpenvasTask>> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.CREATE_TARGET);
        return OpenvasEngine.execute(openvasCommandParse.getXml(createTarget));
    }
}
