package com.javamultiplex.iterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.javamultiplex.Employee;

public class WriteXML {

	public static void main(String[] args) throws IOException,XMLStreamException {

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("src/main/resources/employee.xml");
			System.out.println("How many employees you want to add?");
			int count = Integer.parseInt(input.readLine());
			int empId = 0, age = 0, salary = 0;
			String name = null, role = null;

			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(file));
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent newLine = eventFactory.createDTD("\n");
			StartDocument startDocument = eventFactory.createStartDocument();
			writer.add(startDocument);
			writer.add(newLine);
			StartElement startElement = eventFactory.createStartElement("", "","Employees");
			writer.add(startElement);
			writer.add(newLine);
			EndElement endElement = null;
			Attribute attribute = null;
			for (int i = 1; i <= count; i++) {
				System.out.println("****Employee : " + i + " ****");
				System.out.println("Enter employee id : ");
				empId = Integer.parseInt(input.readLine());
				System.out.println("Enter employee name : ");
				name = input.readLine();
				System.out.println("Enter employee age : ");
				age = Integer.parseInt(input.readLine());
				System.out.println("Enter employee role : ");
				role = input.readLine();
				System.out.println("Enter employee salary : ");
				salary = Integer.parseInt(input.readLine());
				Employee employee = new Employee();
				employee.setId(empId);
				employee.setAge(age);
				employee.setName(name);
				employee.setSalary(salary);
				employee.setRole(role);
				startElement = eventFactory.createStartElement("", "","Employee");
				writer.add(startElement);
				attribute = eventFactory.createAttribute("id",String.valueOf(empId));
				writer.add(attribute);
				writer.add(newLine);
				writeXml(writer, employee);
				endElement = eventFactory.createEndElement("", "", "Employee");
				writer.add(endElement);
				writer.add(newLine);
			}

			endElement = eventFactory.createEndElement("", "", "Employees");
			writer.add(endElement);
			writer.add(newLine);
			EndDocument endDocument = eventFactory.createEndDocument();
			writer.add(endDocument);
			writer.close();
			System.out.println("Employees details has been added successfully.");

		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static void writeXml(XMLEventWriter writer, Employee employee) throws FileNotFoundException, XMLStreamException {

		createPropertyNode(writer, "name", employee.getName());
		createPropertyNode(writer, "age", String.valueOf(employee.getAge()));
		createPropertyNode(writer, "salary",String.valueOf(employee.getSalary()));
		createPropertyNode(writer, "role", employee.getRole());

	}

	private static void createPropertyNode(XMLEventWriter writer,String property, String value) throws XMLStreamException {

		XMLEventFactory factory = XMLEventFactory.newInstance();
		XMLEvent newLine = factory.createDTD("\n");
		XMLEvent tab = factory.createDTD("\t");
		StartElement startElement = factory.createStartElement("", "", property);
		writer.add(tab);
		writer.add(startElement);
		Characters characters = factory.createCharacters(value);
		writer.add(characters);
		EndElement endElement = factory.createEndElement("", "", property);
		writer.add(endElement);
		writer.add(newLine);
	}

}
