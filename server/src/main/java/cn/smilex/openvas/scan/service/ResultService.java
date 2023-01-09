package cn.smilex.openvas.scan.service;

/**
 * @author smilex
 */
public interface ResultService {

    /**
     * 根据任务ID获取任务结果
     *
     * @param taskId 任务ID
     * @return 结果
     */
    String selectResultById(String taskId);
}
