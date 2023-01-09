package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasScanner;
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
public class OpenvasCommandGetScanners implements OpenvasCommandParse<List<OpenvasScanner>> {
    private static final String ROOT_TAG_NAME = "get_scanners";

    @Override
    public List<OpenvasScanner> parse(String xml) {
        try {
            Element root = XmlUtil.getRootElement(XmlUtil.readXML(xml));

            List<Element> taskList = XmlUtil.getElements(root, "scanner");

            if (taskList.size() == 0) {
                return (List<OpenvasScanner>) CommonConfig.EMPTY_LIST;
            }

            List<OpenvasScanner> openvasScannerList = new ArrayList<>(taskList.size());

            for (Element element : taskList) {
                openvasScannerList.add(OpenvasCommandStructParseScanner.getInstance().parse(element));
            }

            return openvasScannerList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (List<OpenvasScanner>) CommonConfig.EMPTY_LIST;
    }

    @Override
    public String getEmptyXml(Object... params) {
        return new XmlTagBuilder(ROOT_TAG_NAME)
                .getXml();
    }
}
