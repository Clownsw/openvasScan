package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smilex
 */
@CrossOrigin
@RestController
@RequestMapping("/config")
public class ConfigController {
    private OpenvasEngine openvasEngine;
    private ConfigService configService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 获取所有config
     *
     * @return config list
     */
    @GetMapping("/list")
    public Result<?> selectAllConfig() {
        return Result.actionSuccess(
                openvasEngine.parse(
                        configService.selectAllConfig(),
                        OpenvasCommand.GET_CONFIGS
                )
        );
    }
}
