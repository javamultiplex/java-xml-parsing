package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ObjectToXML {

	public static void main(String[] args) throws IOException, JAXBException {

		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("src/main/resources/employee.xml");
			int empId = 0, age = 0, salary = 0;
			String name = null, role = null;
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
			convertObjectToXML(employee, file);
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static void convertObjectToXML(Employee employee, File file) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Employee.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(employee, file);
		System.out.println("Object has been successfully converted to XML.");
	}

}
