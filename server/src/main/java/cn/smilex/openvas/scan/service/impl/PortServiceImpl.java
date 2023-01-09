package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasPortList;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.PortService;
import org.springframework.stereotype.Service;

/**
 * @author smilex
 */
@Service
public class PortServiceImpl implements PortService {
    /**
     * 根据 portListId 查询  OpenvasPortList
     *
     * @param portListId portListId
     * @return 结果
     */
    @Override
    public String selectPortListById(String portListId) {
        OpenvasCommandParse<OpenvasPortList> commandParseByCommand = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_PORT_RANGE);
        return OpenvasEngine.execute(commandParseByCommand.getEmptyXml(portListId));
    }
}
