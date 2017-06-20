package com.javamultiplex;

import java.io.File;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class SearchXML {

	public static void main(String[] args) throws DocumentException {

		Scanner input = null;
		try {
			input = new Scanner(System.in);
			File file = new File("src/main/resources/student.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			System.out.println("Enter student roll number : ");
			int rollNumber = input.nextInt();
			Student student = getStudent(document, rollNumber);
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

	private static Student getStudent(Document document, int rollNumber) {

		Student student = null;
		String expression = "/class/student[@rollnumber=" + rollNumber + "]";
		Node node = document.selectSingleNode(expression);
		String firstName = null, lastName = null, nickName = null;
		int marks = 0;
		if (node != null) {
			student = new Student();
			student.setRollNumber(rollNumber);
			firstName = getPropertyValue(node, "firstname");
			student.setFirstName(firstName);
			lastName = getPropertyValue(node, "lastname");
			student.setLastName(lastName);
			nickName = getPropertyValue(node, "nickname");
			student.setNickName(nickName);
			marks = Integer.parseInt(getPropertyValue(node, "marks"));
			student.setMarks(marks);
		}

		return student;
	}

	private static String getPropertyValue(Node node, String property) {

		return node.selectSingleNode(property).getText();
	}
}
