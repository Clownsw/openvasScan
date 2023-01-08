package cn.smilex.openvas.scan.engine.openvas.parse;

import cn.hutool.core.util.XmlUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.engine.openvas.entity.OpenvasTask;
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
public class OpenvasCommandGetTasks implements OpenvasCommandParse<List<OpenvasTask>> {
    private static final String ROOT_TAG_NAME = "get_tasks";

    @Override
    public List<OpenvasTask> parse(String xml) {
        try {
            Element root = XmlUtil.getRootElement(XmlUtil.readXML(xml));

            List<Element> taskList = XmlUtil.getElements(root, "task");

            if (taskList.size() == 0) {
                return (List<OpenvasTask>) CommonConfig.EMPTY_LIST;
            }

            List<OpenvasTask> openvasTaskList = new ArrayList<>(taskList.size());

            for (Element element : taskList) {
                openvasTaskList.add(OpenvasCommandStructParseTask.getInstance().parse(element));
            }

            return openvasTaskList;

        } catch (Exception e) {
            log.error("", e);
        }
        return (List<OpenvasTask>) CommonConfig.EMPTY_LIST;
    }

    @Override
    public String getEmptyXml(Object... params) {
        return new XmlTagBuilder(ROOT_TAG_NAME)
                .getXml();
    }
}
