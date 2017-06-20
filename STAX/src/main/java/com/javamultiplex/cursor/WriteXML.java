package com.javamultiplex.cursor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

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
			XMLStreamWriter writer=factory.createXMLStreamWriter(new FileOutputStream(file));
			writer.writeStartDocument();
			writer.writeCharacters("\n");
			writer.writeStartElement("Employees");
			writer.writeCharacters("\n");
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
				
				writer.writeStartElement("Employee");
				writer.writeAttribute("id", String.valueOf(employee.getId()));
				writer.writeCharacters("\n");
				writeXml(writer, employee);
				writer.writeEndElement();
				writer.writeCharacters("\n");
				
			}
			writer.writeEndElement();
			writer.writeCharacters("\n");
			writer.writeEndDocument();
			writer.close();
			System.out.println("Employees details has been added successfully.");

		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static void writeXml(XMLStreamWriter writer, Employee employee) throws FileNotFoundException, XMLStreamException {

		createPropertyNode(writer, "name", employee.getName());
		createPropertyNode(writer, "age", String.valueOf(employee.getAge()));
		createPropertyNode(writer, "salary",String.valueOf(employee.getSalary()));
		createPropertyNode(writer, "role", employee.getRole());

	}

	private static void createPropertyNode(XMLStreamWriter writer,String property, String value) throws XMLStreamException {

		writer.writeCharacters("\t");
		writer.writeStartElement(property);
		writer.writeCharacters(value);
		writer.writeEndElement();
		writer.writeCharacters("\n");
		
	}

}
