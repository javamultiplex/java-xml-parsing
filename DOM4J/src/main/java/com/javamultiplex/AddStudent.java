package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class AddStudent {

	public static void main(String[] args) throws DocumentException,IOException {

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("src/main/resources/student.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			Student student = new Student();
			System.out.println("Enter roll number : ");
			int rollNumber = Integer.parseInt(input.readLine());
			student.setRollNumber(rollNumber);
			System.out.println("Enter first name : ");
			String firstName = input.readLine();
			student.setFirstName(firstName);
			System.out.println("Enter last name : ");
			String lastName = input.readLine();
			student.setLastName(lastName);
			System.out.println("Enter nick name : ");
			String nickName = input.readLine();
			student.setNickName(nickName);
			System.out.println("Enter marks : ");
			int marks = Integer.parseInt(input.readLine());
			student.setMarks(marks);
			addStudent(student, document);
			FileOutputStream fos = new FileOutputStream(file);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(fos, format);
			writer.write(document);
			System.out.println("Student details has been added successfully.");

		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static void addStudent(Student st, Document document) {

		Element root = document.getRootElement();
		Element student = root.addElement("student").addAttribute("rollnumber",String.valueOf(st.getRollNumber()));
		student.addElement("firstname").addText(st.getFirstName());
		student.addElement("lastname").addText(st.getLastName());
		student.addElement("nickname").addText(st.getNickName());
		student.addElement("marks").addText(String.valueOf(st.getMarks()));

	}

}
