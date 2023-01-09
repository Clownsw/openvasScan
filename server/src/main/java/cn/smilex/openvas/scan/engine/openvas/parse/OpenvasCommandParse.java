package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.OpenvasParams;

import java.util.Map;

/**
 * @author smilex
 */
public interface OpenvasCommandParse<T> {
    /**
     * 解析xml
     *
     * @param xml xml
     * @return result
     */
    T parse(String xml);

    /**
     * 获取空标签
     *
     * @param params 参数
     * @return xml
     */
    default String getEmptyXml(Object... params) {
        return CommonConfig.EMPTY_STRING;
    }

    /**
     * 构建xml
     *
     * @param params params
     * @return xml
     */
    default String getXml(OpenvasParams params) {
        return CommonConfig.EMPTY_STRING;
    }
}
