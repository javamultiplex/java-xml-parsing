package com.javamultiplex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {

	public static void main(String[] args) throws ParserConfigurationException,SAXException, IOException, XPathExpressionException {

		File file = new File("src/main/resources/student.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		System.out.println("Root Element : "+document.getDocumentElement().getNodeName());
		List<Student> students = getStudents(document);
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private static List<Student> getStudents(Document document) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();
		List<Student> students = new ArrayList<>();
		String expression = "/class/student";
		NodeList list = (NodeList) xPath.compile(expression).evaluate(document,XPathConstants.NODESET);
		int length = list.getLength();
		Node node = null;
		Element element = null;
		int rollNumber = 0, marks = 0;
		String firstName = null, lastName = null, nickName = null;
		for (int i = 0; i < length; i++) {
			node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Student student = new Student();
				element = (Element) node;
				rollNumber=Integer.parseInt(getAttributeValue(element,"rollnumber"));
				student.setRollNumber(rollNumber);
				firstName=getPropertyValue(element,"firstname");
				student.setFirstName(firstName);
				lastName=getPropertyValue(element, "lastname");
				student.setLastName(lastName);
				nickName=getPropertyValue(element, "nickname");
				student.setNickName(nickName);
				marks=Integer.parseInt(getPropertyValue(element, "marks"));
				student.setMarks(marks);
				students.add(student);
			}
		}
		return students;

	}

	private static String getPropertyValue(Element element, String property) {
		return element.getElementsByTagName(property).item(0).getTextContent();
	}

	private static String getAttributeValue(Element element, String attribute) {
		return element.getAttribute(attribute);
	}

}
