package com.javamultiplex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySearchHandler extends DefaultHandler {

	private Employee emp = null;
	private int empId = 0;
	boolean bAge = false;
	boolean bName = false;
	boolean bGender = false;
	boolean bRole = false;
	String id = null;

	public MySearchHandler(int empId) {
		this.empId = empId;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("Employee")) {
			id = attributes.getValue("id");
			if (id.equals(String.valueOf(empId))) {
				emp = new Employee();
				emp.setId(Integer.parseInt(id));
			}
		} else if (qName.equalsIgnoreCase("name") && id.equals(String.valueOf(empId))) {
			bName = true;
		} else if (qName.equalsIgnoreCase("age") && id.equals(String.valueOf(empId))) {
			bAge = true;
		} else if (qName.equalsIgnoreCase("gender") && id.equals(String.valueOf(empId))) {
			bGender = true;
		} else if (qName.equalsIgnoreCase("role") && id.equals(String.valueOf(empId))) {
			bRole = true;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

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

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
