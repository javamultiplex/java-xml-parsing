package com.javamultiplex;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyReadHandler extends DefaultHandler {

	// List to hold Employees object
	private List<Employee> empList = null;
	private Employee emp = null;

	// getter method for employee list
	public List<Employee> getEmpList() {
		return empList;
	}

	boolean bAge = false;
	boolean bName = false;
	boolean bGender = false;
	boolean bRole = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("Employee")) {
			String id = attributes.getValue("id");
			emp = new Employee();
			emp.setId(Integer.parseInt(id));
			if (empList == null) {
				empList = new ArrayList<>();
			}
		} else if (qName.equalsIgnoreCase("name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("age")) {
			bAge = true;
		} else if (qName.equalsIgnoreCase("gender")) {
			bGender = true;
		} else if (qName.equalsIgnoreCase("role")) {
			bRole = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Employee")) {
			empList.add(emp);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (bAge) {
			emp.setAge(Integer.parseInt(new String(ch, start, length)));
			bAge = false;
		} else if (bName) {
			emp.setName(new String(ch, start, length));
			bName = false;
		} else if (bRole) {
			emp.setRole(new String(ch, start, length));
			bRole = false;
		} else if (bGender) {
			emp.setGender(new String(ch, start, length));
			bGender = false;
		}

	}
}
