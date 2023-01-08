package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        OpenvasCommandParse<List<OpenvasConfig>> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_CONFIGS);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml());
    }
}
