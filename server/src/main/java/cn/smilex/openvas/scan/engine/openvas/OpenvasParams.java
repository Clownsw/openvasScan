package cn.smilex.openvas.scan.engine.openvas;

import java.util.Map;

/**
 * @author smilex
 */
public interface OpenvasParams {

    /**
     * 转换到Map
     *
     * @return Map
     */
    Map<String, Object> toMap();
}
