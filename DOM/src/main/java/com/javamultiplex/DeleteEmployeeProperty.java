package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DeleteEmployeeProperty {

	public static void main(String[] args) throws IOException,ParserConfigurationException, SAXException, TransformerException {

		File file = new File("src/main/resources/employee.xml");
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			System.out.println("Enter employee id : ");
			int id = Integer.parseInt(input.readLine());
			System.out.println("Enter property name : ");
			String property = input.readLine();
			boolean result = deleteEmployeePropertyFromXml(document, id, property);
			if (result) {
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(document);
				StreamResult sResult = new StreamResult(file);
				transformer.transform(source, sResult);
				System.out.println("Employee property has been deleted successfully.");
			} else {
				System.out.println("Either employee id or property name is wrong.");
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static boolean deleteEmployeePropertyFromXml(Document document,int id, String property) {

		NodeList list = document.getElementsByTagName("Employee");
		boolean result = false;
		int length = list.getLength();
		for (int i = 0; i < length; i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.getAttribute("id").equals(String.valueOf(id))) {
					Node current = element.getElementsByTagName(property).item(0);
					if (current != null) {
						Node prev = current.getPreviousSibling();
						if (prev != null && prev.getNodeType() == Node.TEXT_NODE && prev.getNodeValue().trim().length() == 0) {
							element.removeChild(prev);
						}
						element.removeChild(current);
						result = true;
						break;
					}
				}

			}
		}

		return result;

	}

}
