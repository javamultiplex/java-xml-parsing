package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class DeleteStudentProperty {

	public static void main(String[] args) throws IOException, JDOMException {

		File file = new File("src/main/resources/student.xml");
		PrintWriter out = null;
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(file);
			System.out.println("Enter the roll number : ");
			int rollNumber = Integer.parseInt(input.readLine());
			System.out.println("Enter property : ");
			String property = input.readLine();
			boolean result = deleteStudentPropertyFromXML(rollNumber, property,document);
			if (result) {

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				out = new PrintWriter(file);
				xmlOutput.output(document, out);
				System.out.println("Student property has been deleted successfully.");
			} else {

				System.out.println("Either roll number or property invalid.");
			}

		} finally {
			if (input != null) {
				input.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

	private static boolean deleteStudentPropertyFromXML(int rollNumber,String property, Document document) {

		List<Element> list = document.getRootElement().getChildren();
		int size = list.size();
		Element element = null;
		int studentRollNumber = 0;
		boolean result = false;
		for (int i = 0; i < size; i++) {
			element = list.get(i);
			studentRollNumber = Integer.parseInt(element.getAttributeValue("rollnumber"));
			if (studentRollNumber == rollNumber) {
				result = element.removeChild(property);
				break;

			}
		}

		return result;
	}

}
