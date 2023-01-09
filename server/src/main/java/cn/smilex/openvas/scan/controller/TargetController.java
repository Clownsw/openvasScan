package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.config.ResultCode;
import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.entity.CreateTarget;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.TargetService;
import cn.smilex.openvas.scan.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smilex
 */
@RequestMapping("/target")
@RestController
public class TargetController {
    private OpenvasEngine openvasEngine;
    private TargetService targetService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    /**
     * 创建扫描目标
     *
     * @param createTarget 扫描目标对象
     * @return 结果
     */
    @PostMapping("/createTarget")
    public Result<?> createTarget(@RequestBody CreateTarget createTarget) throws IllegalAccessException {
        if (ClassUtil.objIsNull(CreateTarget.class, createTarget)) {
            return Result.fromResultCode(ResultCode.ERROR_NOT_FOUND_REQUEST_PARAMS);
        }
        return Result.actionSuccess(
                openvasEngine.parse(
                        targetService.createTarget(createTarget),
                        OpenvasCommand.CREATE_TARGET
                )
        );
    }
}
