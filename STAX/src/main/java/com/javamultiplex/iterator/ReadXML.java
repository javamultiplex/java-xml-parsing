package com.javamultiplex.iterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.javamultiplex.Employee;

public class ReadXML {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		File file = new File("src/main/resources/employee.xml");
		List<Employee> employees = getEmployees(file);
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	private static List<Employee> getEmployees(File file) throws FileNotFoundException, XMLStreamException {

		List<Employee> employees = new ArrayList<>();
		Employee employee = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
		StartElement startElement = null;
		EndElement endElement = null;
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {

				startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("Employee")) {
					employee = new Employee();
					String id = getAttributeValue(startElement, "id");
					employee.setId(Integer.parseInt(id));
				} else if (startElement.getName().getLocalPart().equals("age")) {
					String age = getProperty(reader);
					employee.setAge(Integer.parseInt(age));
				} else if (startElement.getName().getLocalPart().equals("name")) {
					String name = getProperty(reader);
					employee.setName(name);
				} else if (startElement.getName().getLocalPart().equals("salary")) {
					String salary = getProperty(reader);
					employee.setSalary(Integer.parseInt(salary));
				} else if (startElement.getName().getLocalPart().equals("role")) {
					String role = getProperty(reader);
					employee.setRole(role);
				}
			}
			if (event.isEndElement()) {

				endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("Employee")) {
					employees.add(employee);
				}

			}
		}
		return employees;
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
