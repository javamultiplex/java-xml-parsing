package com.javamultiplex;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
import org.xml.sax.SAXException;

public class SearchXML {

	public static void main(String[] args) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {

		Scanner input = null;
		try {
			input = new Scanner(System.in);
			System.out.println("Enter student roll number : ");
			int rollNumber = input.nextInt();
			Student student = getStudent(rollNumber);
			if (student != null) {
				System.out.println(student);
			} else {
				System.out.println("Student not exist.");
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static Student getStudent(int rollNumber) throws SAXException,IOException, ParserConfigurationException, XPathExpressionException {

		File file = new File("src/main/resources/student.xml");
		Student student = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "/class/student[@rollnumber=" + rollNumber + "]";
		Node node = (Node) xPath.compile(expression).evaluate(document,XPathConstants.NODE);
		if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			student = new Student();
			Element element = (Element) node;
			student.setRollNumber(rollNumber);
			String firstName = getPropertyValue(element, "firstname");
			student.setFirstName(firstName);
			String lastName = getPropertyValue(element, "lastname");
			student.setLastName(lastName);
			String nickName = getPropertyValue(element, "nickname");
			student.setNickName(nickName);
			int marks = Integer.parseInt(getPropertyValue(element, "marks"));
			student.setMarks(marks);
		}
		return student;
	}

	private static String getPropertyValue(Element element, String property) {
		return element.getElementsByTagName(property).item(0).getTextContent();
	}

}
