package cn.smilex.openvas.scan.engine.openvas.parse;

import org.w3c.dom.Element;

/**
 * @author smilex
 */
public interface OpenvasCommandStructParse<T> {

    /**
     * 解析结构体
     *
     * @param element Element
     * @return 结构体
     */
    T parse(Element element);
}
