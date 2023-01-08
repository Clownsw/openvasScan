package cn.smilex.openvas.scan.engine.openvas.parse;

/**
 * @author smilex
 */
public interface OpenvasCommandParse <T> {
    /**
     * 解析xml
     *
     * @param xml xml
     * @return result
     */
    T parse(String xml);
}
