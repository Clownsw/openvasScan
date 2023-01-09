package cn.smilex.openvas.scan.service;

/**
 * @author smilex
 */
public interface PortService {
    /**
     * 根据 portListId 查询  OpenvasPortList
     *
     * @param portListId portListId
     * @return 结果
     */
    String selectPortListById(String portListId);
}
