package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasConfig;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
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
    private static final String ROOT_TAG_NAME = "get_configs";

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
                openvasConfigList.add(OpenvasCommandStructParseConfig.getInstance().parse(element));
            }

            return openvasConfigList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (List<OpenvasConfig>) CommonConfig.EMPTY_LIST;
    }

    /**
     * 获取空标签
     *
     * @param params 参数
     * @return xml
     */
    @Override
    public String getEmptyXml(Object... params) {
        return new XmlTagBuilder(ROOT_TAG_NAME)
                .getXml();
    }
}
