package com.javamultiplex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadXML {

	public static void main(String[] args) throws JDOMException, IOException {

		File file = new File("src/main/resources/student.xml");
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(file);
		String rootElement = document.getRootElement().getName();
		System.out.println("Root element : " + rootElement);
		List<Student> students = getStudents(document);
		for (Student student : students) {
			System.out.println(student);
		}

	}

	private static List<Student> getStudents(Document document) {

		List<Element> list = document.getRootElement().getChildren();
		List<Student> students = new ArrayList<>();
		int length = list.size();
		Element element = null;
		String firstName = null;
		String lastName = null;
		String nickName = null;
		int rollNumber = 0;
		int marks = 0;
		for (int i = 0; i < length; i++) {
			Student student = new Student();
			element = (Element) list.get(i);
			rollNumber = getAttribute(element, "rollnumber");
			firstName = getProperty(element, "firstname");
			lastName = getProperty(element, "lastname");
			nickName = getProperty(element, "nickname");
			marks = Integer.parseInt(getProperty(element, "marks"));
			student.setRollNumber(rollNumber);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setNickName(nickName);
			student.setMarks(marks);
			students.add(student);
		}
		return students;

	}

	private static String getProperty(Element element, String property) {
		String value = element.getChildText(property);
		return value;
	}

	private static int getAttribute(Element element, String attribute) {

		int value = Integer.parseInt(element.getAttributeValue(attribute));
		return value;

	}

}
