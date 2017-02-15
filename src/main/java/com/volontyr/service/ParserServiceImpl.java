package com.volontyr.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volontyr on 15.02.17.
 */
@Service("xmlParser")
public class ParserServiceImpl implements ParserService {

    public List<String> parse(String nodeName, int cnt) {
        Document doc = null;
        List<String> nodes = new ArrayList<>();
        try {
            String filePath = this.getClass().getClassLoader().getResource("test.xml").getFile();
            doc = getXMLDocument(filePath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            NodeList nodeList = doc.getElementsByTagName(nodeName);

            int len;
            if (cnt > nodeList.getLength()) {
                len = nodeList.getLength();
            } else {
                len = cnt;
            }

            for (int i = 0; i < len; i++) {
                Node nNode = nodeList.item(i);
                nodes.add(nNode.getFirstChild().getTextContent());
            }

            if (nodes.isEmpty()) {
                nodes.add("There's no result for your request");
            }
        }
        return nodes;
    }

    private Document getXMLDocument(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(filePath);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
