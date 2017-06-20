package com.javamultiplex.cursor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.javamultiplex.Employee;

public class ReadXML {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		File file = new File("src/main/resources/employee.xml");
		List<Employee> employees = getEmployees(file);
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	private static List<Employee> getEmployees(File file) throws XMLStreamException, FileNotFoundException {

		List<Employee> employees = new ArrayList<>();
		Employee employee = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
		int event = reader.next();
		boolean bAge = false, bName = false, bSalary = false, bRole = false;
		while (true) {
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				if (reader.getLocalName().equals("Employee")) {
					employee = new Employee();
					employee.setId(Integer.parseInt(reader.getAttributeValue(0)));
				} else if (reader.getLocalName().equals("age")) {
					bAge = true;
				} else if (reader.getLocalName().equals("name")) {
					bName = true;
				} else if (reader.getLocalName().equals("salary")) {
					bSalary = true;
				} else if (reader.getLocalName().equals("role")) {
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
			case XMLStreamConstants.END_ELEMENT:

				if (reader.getLocalName().equals("Employee")) {
					employees.add(employee);
				}
				break;

			}

			if (!reader.hasNext()) {
				break;
			}
			event = reader.next();

		}
		return employees;
	}

}
