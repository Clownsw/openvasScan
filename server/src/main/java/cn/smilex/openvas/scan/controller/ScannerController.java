package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.ScannerService;
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
@RequestMapping("/scanner")
public class ScannerController {
    private OpenvasEngine openvasEngine;
    private ScannerService scannerService;

    @Autowired
    public void setOpenvasEngine(OpenvasEngine openvasEngine) {
        this.openvasEngine = openvasEngine;
    }

    @Autowired
    public void setScannerService(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    /**
     * 查询所有扫描器
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<?> list() {
        return Result.actionSuccess(
                openvasEngine.parse(
                        scannerService.selectAllScanner(),
                        OpenvasCommand.GET_SCANNERS
                )
        );
    }
}
