package cn.smilex.openvas.scan.service;

import cn.smilex.openvas.scan.entity.CreateTarget;

/**
 * @author smilex
 */
public interface TargetService {

    /**
     * 查询所有扫描目标
     *
     * @return 结果
     */
    String selectAllTarget();

    /**
     * 创建扫描目标
     *
     * @param createTarget 扫描目标对象
     * @return 结果
     */
    String createTarget(CreateTarget createTarget);
}
