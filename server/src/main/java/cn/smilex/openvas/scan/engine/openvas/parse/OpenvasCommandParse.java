package cn.smilex.openvas.scan.engine.openvas.parse;

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
    String getEmptyXml(Object... params);
}
