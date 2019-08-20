package com.zbl.code.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @Author: zbl
 * @Date: Created in 17:11 2019/8/20
 * @Description:  xml数据解析
 * @Version: $
 */
public class XmlUtil {

    public static Map<String, String> xml2Map(String text) {
        Map<String, String> data = new LinkedHashMap<>();
        try {
            Document doc = DocumentHelper.parseText(text);
            Element root = doc.getRootElement();
            String path = "/" + root.getName();
            element2Map(root, data, path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static void element2Map(Element ele, Map<String, String> data, String path) {
        if (ele == null) {
            return;
        }
        List<Element> childrens = ele.elements();
        if (childrens != null && childrens.size() > 0) {
            Element pre = null;
            Element cur = null;
            Element next = null;
            int nodeIndex = 1;
            int length = childrens.size();
            for (int i = 0; i < length; i++) {
                cur = childrens.get(i);
                String nodePath = path + "/" + cur.getName();
                if (pre == null) {
                    next = childrens.get(i + 1);
                    if (next.getName().equals(cur.getName())) {
                        nodePath += "[" + nodeIndex + "]";
                        nodeIndex++;
                    }
                } else {
                    if (pre.getName().equals(cur.getName())) {
                        nodePath += "[" + nodeIndex + "]";
                        nodeIndex++;
                    } else {
                        nodeIndex = 1;
                    }
                }
                element2Map(cur, data, nodePath);
                pre = cur;
            }
        } else {

            data.put(path, ele.getText());

        }

    }


    public static String map2Xml(Map<String, String> map) {
        String xml;
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xml");
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> e = it.next();
            String key = e.getKey();
            String value = e.getValue();

            Element ele = root.addElement(key);
            ele.setText(value);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setExpandEmptyElements(false);
        StringWriter out = new StringWriter();
        XMLWriter writer = new XMLWriter(out, format);
        try {
            writer.write(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        xml = out.toString();
        xml = xml.replaceAll("\\[\\d*\\]", "");
        return xml;
    }
}
