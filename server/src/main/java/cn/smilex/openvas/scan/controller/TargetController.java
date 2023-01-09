package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.config.ResultCode;
import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTarget;
import cn.smilex.openvas.scan.entity.CreateTarget;
import cn.smilex.openvas.scan.entity.StructuredTaskScope;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.PortService;
import cn.smilex.openvas.scan.service.TargetService;
import cn.smilex.openvas.scan.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author smilex
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/target")
public class TargetController {
    private OpenvasEngine openvasEngine;
    private TargetService targetService;
    private PortService portService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    @Autowired
    public void setPortService(PortService portService) {
        this.portService = portService;
    }

    /**
     * 查询所有扫描目标
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<?> selectAllTarget() {
        List<OpenvasTarget> openvasTargetList = openvasEngine.parse(
                targetService.selectAllTarget(),
                OpenvasCommand.GET_TARGETS
        );

        if (openvasTargetList.size() > 0) {
            try (StructuredTaskScope scope = new StructuredTaskScope(true, openvasTargetList.size())) {

                for (OpenvasTarget openvasTarget : openvasTargetList) {
                    scope.fork(() -> openvasTarget.setOpenvasPortList(
                            openvasEngine.parse(
                                    portService.selectPortListById(openvasTarget.getPortListId()), OpenvasCommand.GET_PORT_RANGE)));
                }

            } catch (Exception e) {
                log.error("", e);
            }
        }

        return Result.actionSuccess(openvasTargetList);
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
