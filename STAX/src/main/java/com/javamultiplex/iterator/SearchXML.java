package com.javamultiplex.iterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

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

	private static Employee getEmployees(File file, int empId) throws FileNotFoundException, XMLStreamException {

		Employee employee = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
		StartElement startElement = null;
		String id = null;
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {

				startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("Employee")) {
					id = getAttributeValue(startElement, "id");
					if (id.equals(String.valueOf(empId))) {
						employee = new Employee();
						employee.setId(Integer.parseInt(id));
					}
				} else if (startElement.getName().getLocalPart().equals("age") && id.equals(String.valueOf(empId))) {
					String age = getProperty(reader);
					employee.setAge(Integer.parseInt(age));
				} else if (startElement.getName().getLocalPart().equals("name") && id.equals(String.valueOf(empId))) {
					String name = getProperty(reader);
					employee.setName(name);
				} else if (startElement.getName().getLocalPart().equals("salary") && id.equals(String.valueOf(empId))) {
					String salary = getProperty(reader);
					employee.setSalary(Integer.parseInt(salary));
				} else if (startElement.getName().getLocalPart().equals("role") && id.equals(String.valueOf(empId))) {
					String role = getProperty(reader);
					employee.setRole(role);
				}
			}

		}
		return employee;
	}

	private static String getProperty(XMLEventReader reader) throws XMLStreamException {

		XMLEvent event = reader.nextEvent();
		Characters characters = null;
		if (event.isCharacters()) {
			characters = event.asCharacters();
		}
		return characters.getData();
	}

	private static String getAttributeValue(StartElement startElement, String attribute) {

		Attribute attr = startElement.getAttributeByName(new QName(attribute));
		return attr.getValue();
	}

}
