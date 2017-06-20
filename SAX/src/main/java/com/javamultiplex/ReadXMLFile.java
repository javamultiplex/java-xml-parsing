package com.javamultiplex;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFile {

	public static void main(String[] args) {

		File file = new File("src/main/resources/employee.xml");
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			MyReadHandler handler = new MyReadHandler();
			parser.parse(file, handler);
			List<Employee> list = handler.getEmpList();
			for (Employee emp : list) {
				System.out.println(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
