package cn.smilex.openvas.scan.controller;

import cn.smilex.openvas.scan.pojo.Result;
import cn.smilex.openvas.scan.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smilex
 */
@RequestMapping("/result")
@RestController
public class ResultController {
    private ResultService resultService;

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
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
                resultService.selectResultById(taskId)
        );
    }
}
