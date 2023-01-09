package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.ReportService;
import cn.smilex.openvas.scan.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author smilex
 */
@CrossOrigin
@RestController
@RequestMapping("/result")
public class ResultController {
    private ReportService reportService;

    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 根据任务ID获取任务结果
     *
     * @param taskId 任务ID
     * @return 结果
     */
    @GetMapping("/one")
    public Result<?> selectResultById(@RequestParam("taskId") String taskId) {
        return Result.actionSuccess(
                reportService.selectReportById(
                        Objects.requireNonNull(TaskServiceImpl.getOpenvasTaskIdById(taskId)).getRight()
                )
        );
    }
}
