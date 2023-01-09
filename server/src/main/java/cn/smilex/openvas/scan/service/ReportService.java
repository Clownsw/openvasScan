package cn.smilex.openvas.scan.service;

/**
 * @author smilex
 */
public interface ReportService {

    /**
     * 根据ID查询报告
     *
     * @param id id
     * @return 结果
     */
    String selectReportById(String id);
}
