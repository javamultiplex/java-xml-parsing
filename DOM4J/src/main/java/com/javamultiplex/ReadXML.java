package com.javamultiplex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ReadXML {

	public static void main(String[] args) throws DocumentException {

		File file = new File("src/main/resources/student.xml");
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		String rootElement = document.getRootElement().getName();
		System.out.println("Root Element : " + rootElement);
		List<Student> students = getStudents(document);
		for (Student student : students) {
			System.out.println(student);
		}

	}

	private static List<Student> getStudents(Document document) {

		List<Student> students = new ArrayList<>();
		Student student = null;
		String expression = "/class/student";
		@SuppressWarnings("unchecked")
		List<Node> nodes = document.selectNodes(expression);
		int rollNumber = 0, marks = 0;
		String firstName = null, lastName = null, nickName = null;
		for (Node node : nodes) {
			student = new Student();
			rollNumber = getAttributeValue(node, "rollnumber");
			student.setRollNumber(rollNumber);
			firstName = getPropertyValue(node, "firstname");
			student.setFirstName(firstName);
			lastName = getPropertyValue(node, "lastname");
			student.setLastName(lastName);
			nickName = getPropertyValue(node, "nickname");
			student.setNickName(nickName);
			marks = Integer.parseInt(getPropertyValue(node, "marks"));
			student.setMarks(marks);
			students.add(student);
		}

		return students;
	}

	private static String getPropertyValue(Node node, String property) {

		return node.selectSingleNode(property).getText();
	}

	private static int getAttributeValue(Node node, String attribute) {
		return Integer.parseInt(node.valueOf("@" + attribute));
	}
}
