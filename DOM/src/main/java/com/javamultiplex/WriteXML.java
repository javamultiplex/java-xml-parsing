package com.javamultiplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML {

	public static void main(String[] args) throws IOException {

		BufferedReader input = null;

		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			File file = new File("src/main/resources/employee.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("Employees");
			document.appendChild(root);
			System.out.println("Enter how many employess data you want to add? : ");
			int count = Integer.parseInt(input.readLine());
			String name = null, gender = null, role = null;
			int age = 0;
			Element element = null;
			for (int i = 1; i <= count; i++) {
				System.out.println("******* Employee : " + i + " ********");
				System.out.println("Enter name : ");
				name = input.readLine();
				System.out.println("Enter gender : (male/female)");
				gender = input.readLine();
				System.out.println("Enter age : ");
				age = Integer.parseInt(input.readLine());
				System.out.println("Enter role : ");
				role = input.readLine();
				Employee employee = new Employee();
				employee.setName(name);
				employee.setGender(gender);
				employee.setAge(age);
				employee.setRole(role);
				element = getEmployeeNode(employee, i, document);
				root.appendChild(element);
			}

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			System.out.println("Employee data has been added successfully.");

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}

	}

	private static Element getEmployeeNode(Employee employee, int id,Document document) {

		Element element = document.createElement("Employee");
		element.setAttribute("id", String.valueOf(id));
		Element name = getPropertyNode("name", document, employee.getName());
		element.appendChild(name);
		Element gender = getPropertyNode("gender", document,employee.getGender());
		element.appendChild(gender);
		Element age = getPropertyNode("age", document,String.valueOf(employee.getAge()));
		element.appendChild(age);
		Element role = getPropertyNode("role", document, employee.getRole());
		element.appendChild(role);
		return element;
	}

	private static Element getPropertyNode(String property, Document document,String value) {

		Element element = document.createElement(property);
		element.setTextContent(value);
		return element;
	}

}
