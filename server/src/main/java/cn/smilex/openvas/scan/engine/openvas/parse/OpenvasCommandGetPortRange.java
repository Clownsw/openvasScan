package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasPortList;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasPortRange;
import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.openvas.scan.pojo.XmlTagBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @author smilex
 */
@Slf4j
public class OpenvasCommandGetPortRange implements OpenvasCommandParse<OpenvasPortList> {
    private static final String ROOT_TAG_NAME = "get_port_lists";

    @Override
    public OpenvasPortList parse(String xml) {
        try {
            Element root = XmlUtil.getRootElement(XmlUtil.readXML(xml));

            List<Element> portList = XmlUtil.getElements(root, "port_list");

            if (portList.size() == 0) {
                return (OpenvasPortList) CommonConfig.EMPTY_OBJECT;
            }

            OpenvasPortList openvasPortList = new OpenvasPortList();
            List<OpenvasPortRange> openvasPortRangeList = openvasPortList.getOpenvasPortRangeList();

            for (Element element : portList) {
                Element portRanges = XmlUtil.getElement(element, "port_ranges");

                if (portRanges == null) {
                    return openvasPortList;
                }

                List<Element> portRangeList = XmlUtil.getElements(portRanges, "port_range");
                for (Element portRange : portRangeList) {
                    openvasPortRangeList.add(OpenvasCommandStructParsePortRange.getInstance().parse(portRange));
                }
            }

            return openvasPortList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (OpenvasPortList) CommonConfig.EMPTY_OBJECT;
    }

    @Override
    public String getEmptyXml(Object... params) {
        if (params.length == 1 && params[0] instanceof String && StringUtils.isNoneBlank((String) params[0])) {
            return new XmlTagBuilder(
                    ROOT_TAG_NAME,
                    new HashMapBuilder<String, Object>(2)
                            .put("port_list_id", params[0])
                            .put("details", "1")
                            .get()
            )
                    .getXml();
        }

        throw new IllegalArgumentException();
    }
}
