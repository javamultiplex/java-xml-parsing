package com.javamultiplex;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class SearchXML {

	public static void main(String[] args) throws JDOMException, IOException {

		File file = new File("src/main/resources/student.xml");
		Scanner input = null;
		try {
			input = new Scanner(System.in);
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(file);
			System.out.println("Enter roll number : ");
			int rollNumber = input.nextInt();
			Student student = getStudent(document, rollNumber);
			if (student == null) {
				System.out.println("Student not exist with roll number = " + rollNumber);
			} else {
				System.out.println(student);
			}

		} finally {
			if (input != null){
				input.close();
			}
		}
	}

	private static Student getStudent(Document document, int rollNumber) {

		List<Element> list = document.getRootElement().getChildren();
		int size = list.size();
		Student student = null;
		String firstName = null, lastName = null, nickName = null;
		int marks = 0, studentRollNumber = 0;
		Element element = null;
		for (int i = 0; i < size; i++) {

			element = list.get(i);
			studentRollNumber = Integer.parseInt(element.getAttributeValue("rollnumber"));

			if (rollNumber == studentRollNumber) {
				student = new Student();
				student.setRollNumber(studentRollNumber);
				firstName = element.getChildText("firstname");
				student.setFirstName(firstName);
				lastName = element.getChildText("lastname");
				student.setLastName(lastName);
				nickName = element.getChildText("nickname");
				student.setNickName(nickName);
				marks = Integer.parseInt(element.getChildText("marks"));
				student.setMarks(marks);
			}

		}
		return student;
	}

}
