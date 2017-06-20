package com.javamultiplex;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLToObject {

	public static void main(String[] args) throws JAXBException {
		
		File file=new File("src/main/resources/employee.xml");
		JAXBContext context=JAXBContext.newInstance(Employee.class);
		Unmarshaller unmarshaller=context.createUnmarshaller();
		Employee employee=(Employee) unmarshaller.unmarshal(file);
		System.out.println(employee);
	}
	
}
