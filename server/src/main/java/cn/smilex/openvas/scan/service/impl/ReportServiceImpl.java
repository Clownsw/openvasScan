package cn.smilex.openvas.scan.service.impl;

import cn.smilex.openvas.scan.engine.openvas.OpenvasCommand;
import cn.smilex.openvas.scan.engine.openvas.OpenvasEngine;
import cn.smilex.openvas.scan.engine.openvas.parse.OpenvasCommandParse;
import cn.smilex.openvas.scan.service.ReportService;
import org.springframework.stereotype.Service;

/**
 * @author smilex
 */
@Service
public class ReportServiceImpl implements ReportService {

    /**
     * 根据ID查询报告
     *
     * @param id id
     * @return 结果
     */
    @Override
    public String selectReportById(String id) {
        OpenvasCommandParse<Object> openvasCommandParse = OpenvasEngine.getCommandParseByCommand(OpenvasCommand.GET_REPORT);
        return OpenvasEngine.execute(openvasCommandParse.getEmptyXml(id));
    }
}
