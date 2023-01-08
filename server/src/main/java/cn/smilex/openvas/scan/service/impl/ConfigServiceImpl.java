package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return openvasEngine.execute(openvasCommandParse.getEmptyXml());
    }
}
