package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class AddStudent {

	public static void main(String[] args) throws NumberFormatException, IOException, JDOMException {

		File file = new File("src/main/resources/student.xml");
		PrintWriter out = null;
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(file);
			Student student = new Student();
			System.out.println("Enter roll number : ");
			int rollNumber = Integer.parseInt(input.readLine());
			System.out.println("Enter first name : ");
			String firstName = input.readLine();
			System.out.println("Enter last name : ");
			String lastName = input.readLine();
			System.out.println("Enter nick name : ");
			String nickName = input.readLine();
			System.out.println("Enter marks : ");
			int marks = Integer.parseInt(input.readLine());
			student.setRollNumber(rollNumber);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setNickName(nickName);
			student.setMarks(marks);
			Element myStudent = addChild(document, student);
			document.getRootElement().addContent(myStudent);
			out = new PrintWriter(file);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, out);
			System.out.println("Student details has been added successfully.");
		} finally {

			if (input != null) {
				input.close();
			}
			if(out!=null){
				out.close();
			}
		}

	}

	private static Element addChild(Document document, Student student) {

		Element myStudent = new Element("Student");
		Attribute rollNumber = createAttribute("rollnumber", student.getRollNumber());
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
