package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class WriteXML {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader input = null;
		PrintWriter out = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			Element students = new Element("Students");
			Document document = new Document();
			document.setRootElement(students);
			System.out.println("How many students you want to add?");
			int count = Integer.parseInt(input.readLine());
			int rollNumber = 0, marks = 0;
			String firstName = null, lastName = null, nickName = null;
			for (int i = 1; i <= count; i++) {
				Student student = new Student();
				System.out.println("****** Student : " + i + " *******");
				System.out.println("Enter roll number : ");
				rollNumber = Integer.parseInt(input.readLine());
				System.out.println("Enter first name : ");
				firstName = input.readLine();
				System.out.println("Enter last name : ");
				lastName = input.readLine();
				System.out.println("Enter nick name : ");
				nickName = input.readLine();
				System.out.println("Enter marks : ");
				marks = Integer.parseInt(input.readLine());
				student.setRollNumber(rollNumber);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setNickName(nickName);
				student.setMarks(marks);
				Element myStudent = createChildrens(document, student);
				document.getRootElement().addContent(myStudent);
			}
			File file = new File("src/main/resources/student.xml");
			out = new PrintWriter(file);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, out);
			System.out.println("Student details has been added successfully.");
		} finally {

			if (input != null) {
				input.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

	private static Element createChildrens(Document document, Student student) {

		Element myStudent = new Element("Student");
		Attribute rollNumber = createAttribute("rollnumber",student.getRollNumber());
		myStudent.setAttribute(rollNumber);
		Element firstName = createChild("firstname", student.getFirstName());
		myStudent.addContent(firstName);
		Element lastName = createChild("lastname", student.getLastName());
		myStudent.addContent(lastName);
		Element nickName = createChild("nickname", student.getNickName());
		myStudent.addContent(nickName);
		Element marks = createChild("marks", String.valueOf(student.getMarks()));
		myStudent.addContent(marks);
		return myStudent;

	}

	private static Element createChild(String property, String value) {

		Element element = new Element(property);
		element.setText(value);
		return element;
	}

	private static Attribute createAttribute(String attribute, int value) {

		Attribute attr = new Attribute(attribute, String.valueOf(value));
		return attr;
	}

}
