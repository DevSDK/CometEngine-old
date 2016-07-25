package com.CometEngine.Font.BMPFont;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FntFileXML {

	private Element[] NowElements;
	private int NowElementPointer = 0;
	private Document XMLDocument = null;

	public FntFileXML(ByteBuffer buffer) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			XMLDocument = dBuilder.parse(new ByteArrayInputStream(buffer.array()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setElementSet(String tag) {
		NowElementPointer = 0;
		NowElements = getElements(tag);
	}

	protected String getAttribute(String name) {
		return NowElements[NowElementPointer].getAttribute(name);
	}

	protected boolean ProcessesElementCounter() {
		NowElementPointer++;
		if (NowElementPointer >= NowElements.length)
			return false;
		return true;
	}

	private Element[] getElements(String tag) {
		NodeList list = XMLDocument.getElementsByTagName(tag);
		ArrayList<Element> arraylist = new ArrayList<Element>();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				arraylist.add(element);
			}
		}
		Element[] array = new Element[arraylist.size()];
		arraylist.toArray(array);
		return array;

	}

	private void loadXML(ByteBuffer file) {

	}

}
