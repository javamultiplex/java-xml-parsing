package com.javamultiplex;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SearchXMLFile {

	public static void main(String[] args) {

		File file = new File("src/main/resources/employee.xml");
		Scanner input = null;
		try {

			input = new Scanner(System.in);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			System.out.println("Enter employee id : ");
			int id = input.nextInt();
			MySearchHandler handler = new MySearchHandler(id);
			parser.parse(file, handler);
			Employee emp = handler.getEmp();
			if (emp != null) {
				System.out.println(emp);
			} else {
				System.out.println("Employee not exist.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

}
