package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smilex
 */
@SuppressWarnings("unchecked")
@Slf4j
public class OpenvasCommandGetConfigs implements OpenvasCommandParse<List<OpenvasConfig>> {
    /**
     * 解析xml
     *
     * @param xml xml
     * @return result
     */
    @Override
    public List<OpenvasConfig> parse(String xml) {
        try {
            Element root = XmlUtil.getRootElement(XmlUtil.readXML(xml));

            List<Element> configList = XmlUtil.getElements(root, "config");

            if (configList.size() == 0) {
                return (List<OpenvasConfig>) CommonConfig.EMPTY_LIST;
            }

            List<OpenvasConfig> openvasConfigList = new ArrayList<>(configList.size());

            for (Element element : configList) {
                openvasConfigList.add(
                        new OpenvasConfig(
                                element.getAttribute("id"),
                                XmlUtil.getElement(element, "name").getFirstChild().getTextContent(),
                                XmlUtil.getElement(element, "comment").getFirstChild().getTextContent(),
                                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "creation_time").getFirstChild().getTextContent()),
                                CommonUtil.parseUtcTime(XmlUtil.getElement(element, "modification_time").getFirstChild().getTextContent())
                        )
                );
            }

            return openvasConfigList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (List<OpenvasConfig>) CommonConfig.EMPTY_LIST;
    }
}
