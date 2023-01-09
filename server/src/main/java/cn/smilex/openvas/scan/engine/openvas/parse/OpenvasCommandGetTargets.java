package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTarget;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smilex
 */
@Slf4j
@SuppressWarnings("unchecked")
public class OpenvasCommandGetTargets implements OpenvasCommandParse<List<OpenvasTarget>> {
    private static final String ROOT_TAG_NAME = "get_targets";

    @Override
    public List<OpenvasTarget> parse(String xml) {
        try {
            Element root = XmlUtil.getRootElement(XmlUtil.readXML(xml));

            List<Element> configList = XmlUtil.getElements(root, "target");

            if (configList.size() == 0) {
                return (List<OpenvasTarget>) CommonConfig.EMPTY_LIST;
            }

            List<OpenvasTarget> openvasTargetList = new ArrayList<>(configList.size());

            for (Element element : configList) {
                openvasTargetList.add(OpenvasCommandStructParseTarget.getInstance().parse(element));
            }

            return openvasTargetList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (List<OpenvasTarget>) CommonConfig.EMPTY_LIST;
    }

    @Override
    public String getEmptyXml(Object... params) {
        return new XmlTagBuilder(ROOT_TAG_NAME)
                .getXml();
    }
}
