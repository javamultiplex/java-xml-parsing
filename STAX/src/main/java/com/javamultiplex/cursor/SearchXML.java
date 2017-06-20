package com.javamultiplex.cursor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.javamultiplex.Employee;

public class SearchXML {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		Scanner input = null;
		try {
			input = new Scanner(System.in);
			File file = new File("src/main/resources/employee.xml");
			System.out.println("Enter employee id : ");
			int id = input.nextInt();
			Employee employees = getEmployees(file, id);
			if (employees != null) {
				System.out.println(employees);
			} else {
				System.out.println("Employee not exist.");
			}
		} finally {

			if (input != null) {
				input.close();
			}
		}
	}

	private static Employee getEmployees(File file, int empId) throws XMLStreamException, FileNotFoundException {

		Employee employee = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
		int event = reader.next();
		boolean bAge = false, bName = false, bSalary = false, bRole = false;
		String id=null;
		while (true) {
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if (reader.getLocalName().equals("Employee")) {
					id=reader.getAttributeValue(0);
					if(id.equals(String.valueOf(empId))){
						employee = new Employee();
						employee.setId(Integer.parseInt(id));
					}
					
				} else if (reader.getLocalName().equals("age") && id.equals(String.valueOf(empId)) ) {
					bAge = true;
				} else if (reader.getLocalName().equals("name") && id.equals(String.valueOf(empId))) {
					bName = true;
				} else if (reader.getLocalName().equals("salary") && id.equals(String.valueOf(empId))) {
					bSalary = true;
				} else if (reader.getLocalName().equals("role") && id.equals(String.valueOf(empId))) {
					bRole = true;
				}
				break;
			case XMLStreamConstants.CHARACTERS:

				if (bAge) {
					employee.setAge(Integer.parseInt(reader.getText()));
					bAge = false;
				} else if (bName) {
					employee.setName(reader.getText());
					bName = false;
				} else if (bSalary) {
					employee.setSalary(Integer.parseInt(reader.getText()));
					bSalary = false;
				} else if (bRole) {
					employee.setRole(reader.getText());
					bRole = false;
				}
				break;
			}

			if (!reader.hasNext()) {
				break;
			}
			event = reader.next();

		}
		return employee;
	}

}
